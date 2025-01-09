package ikefercol;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        File json = new File("src/main/resources/datos.json");

        Properties props = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/config.ini")) {
            props.load(inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        String nombreconfig = props.getProperty("nombre");
        String temaconfig = props.getProperty("tema");

        ObjectMapper mapper = new ObjectMapper();
        Liga liga = mapper.readValue(json, Liga.class);

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("nombre", nombreconfig);
        context.setVariable("tema", temaconfig);
        context.setVariable("laliga", liga.getLaliga());
        context.setVariable("premierleague", liga.getPremierleague());

        String indexTemplate = templateEngine.process("plantilla_index", context);
        System.out.println(indexTemplate);
        escribirHTML(indexTemplate, "src/main/resources/Webs/index.html");
        GuardarCache("index.html", indexTemplate);

        List<Equipo> equipos = liga.getLaliga();
        for (Equipo e : equipos) {
            context.setVariable("equipo", e.getEquipo());
            context.setVariable("lugar", e.getLugar());
            context.setVariable("mejor_jugador", e.getMejorJugador());
            context.setVariable("imagen", e.getImagen());
            String equipoTemplate = templateEngine.process("plantilla_equipo", context);
            String filename = "src/main/resources/Webs/equipo/" + e.getEquipo().replaceAll("\\s+", "_") + ".html";
            escribirHTML(equipoTemplate, filename);
            GuardarCache(filename, equipoTemplate);
        }
        List<Equipo> equipospremier = liga.getPremierleague();
        for (Equipo e : equipospremier) {
            context.setVariable("equipo", e.getEquipo());
            context.setVariable("lugar", e.getLugar());
            context.setVariable("mejor_jugador", e.getMejorJugador());
            context.setVariable("imagen", e.getImagen());
            String equipoTemplate = templateEngine.process("plantilla_equipo", context);
            String filename = "src/main/resources/Webs/equipo/" + e.getEquipo().replaceAll("\\s+", "_") + ".html";
            escribirHTML(equipoTemplate, filename);
            GuardarCache(filename, equipoTemplate);
        }

        GenerarRssXML(liga, "src/main/resources/rss.xml", nombreconfig, temaconfig);


    }

    public static void GenerarRssXML(Liga liga, String rutarss, String nombre, String descripcion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutarss))) {
            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<rss version=\"2.0\">\n");
            writer.write("<channel>\n");
            writer.write("<title>" + nombre + "</title>\n");
            writer.write("<link>src/main/resources/Webs/index.html</link>\n");
            writer.write("<description>" + descripcion + "</description>\n");

            for (Equipo equipo : liga.getLaliga()) {
                writer.write("<item>\n");
                writer.write("<title>" + equipo.getEquipo() + "</title>\n");
                writer.write("<link>src/main/resources/Webs/equipo/" + equipo.getEquipo().replaceAll("\\s+", "_") + ".html</link>\n");
                writer.write("<description>Mejor jugador: " + equipo.getMejorJugador() + "</description>\n");
                writer.write("</item>\n");
            }

            for (Equipo equipo : liga.getPremierleague()) {
                writer.write("<item>\n");
                writer.write("<title>" + equipo.getEquipo() + "</title>\n");
                writer.write("<link>src/main/resources/Webs/equipo/" + equipo.getEquipo().replaceAll("\\s+", "_") + ".html</link>\n");
                writer.write("<description>Mejor jugador: " + equipo.getMejorJugador() + "</description>\n");
                writer.write("</item>\n");
            }

            writer.write("</channel>\n");
            writer.write("</rss>\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error al generar el archivo RSS", e);
        }
    }

    public static void GuardarCache(String archivo, String contenido) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/memoriacache.dat", true))) {
            writer.write(archivo + "|" + contenido + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    public static void escribirHTML(String html, String nombrefichero) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombrefichero))) {
            writer.write(html);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error al escribir el archivo HTML", e);
        }

    }


}
