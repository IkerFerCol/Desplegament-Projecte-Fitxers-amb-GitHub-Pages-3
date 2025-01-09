package ikefercol;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Equipo {
    @JsonProperty("equipo")
    private String equipo;
    @JsonProperty("lugar")
    private String lugar;
    @JsonProperty("mejor_jugador")
    private String mejorJugador;
    @JsonProperty("imagen")
    private String imagen;

    public Equipo(String equipo, String lugar, String mejorJugador, String imagen) {
        this.equipo = equipo;
        this.lugar = lugar;
        this.mejorJugador = mejorJugador;
        this.imagen = imagen;
    }

    public Equipo(){}

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getMejorJugador() {
        return mejorJugador;
    }

    public void setMejorJugador(String mejorJugador) {
        this.mejorJugador = mejorJugador;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "equipo='" + equipo + '\'' +
                ", lugar='" + lugar + '\'' +
                ", mejorJugador='" + mejorJugador + '\'' +
                ", imagen='" + imagen + '\'' +
                '}';
    }
}
