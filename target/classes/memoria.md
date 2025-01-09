# EQUIPOS DE FÚTBOL

**Autor:** Iker Fernández  
**Fecha:** 18/11/2024  
**Asignatura:** Acceso a Datos

---

## ÍNDICE

1. [DESCRIPCIÓN](#1-descripción)
2. [FICHEROS DE ENTRADA](#2-ficheros-de-entrada)  
   2.1 [CONFIG.INI](#21-configini)  
   2.2 [JSON](#22-json)  
   2.3 [SCHEMA](#23-schema)
3. [CLASES](#3-clases)
4. [FICHEROS DE SALIDA](#4-ficheros-de-salida)  
   4.1 [XML RSS](#41-xml-rss)  
   4.2 [CAPTURAS DE LAS PÁGINAS](#42-capturas-de-las-páginas)

---

## 1. DESCRIPCIÓN

Este proyecto consiste en un generador de páginas web con la temática de los mejores equipos de Laliga y Premier League de fútbol, utilizando las plantillas Thymeleaf, archivos JSON con SCHEMA, un CONFIG.INI para definir el nombre, un XML RSS validado, y un ObjectMapper para parsear el JSON.

---

## 2. FICHEROS DE ENTRADA

### 2.1 CONFIG.INI
Es un archivo de configuración usado para almacenar parámetros de configuración de una aplicación estructurada y fácil de leer. Este es mi CONFIG.INI del proyecto:

[seccion]  
nombre = Futbol Internacional  
tema = Competiciones futbolisticas

---

### 2.2 JSON

El archivo JSON contiene los datos que tienen las ligas con sus equipos y sus mejores jugadores. Si añades un equipo, también se generará un archivo HTML del mismo.

---

### 2.3 SCHEMA

Es un conjunto de reglas que define la estructura y la organización de un conjunto de datos. Se utiliza en el contexto de bases de datos y lenguajes de marcado como JSON y XML.

---

## 3. CLASES

### Clase Equipo

La clase **Equipo** representa la unidad básica de información en el sistema: un equipo de fútbol. Su propósito es encapsular los datos relacionados con un equipo en un único objeto que puede manipularse uniformemente.

- **Organizar información:** Mantiene todos los atributos relevantes de un equipo (nombre, ubicación, mejor jugador e imagen) en una estructura coherente.
- **Facilitar el procesamiento de datos:** Sus métodos permiten acceder o modificar fácilmente los atributos del equipo.
- **Compatibilidad con JSON:** Está diseñada para mapear automáticamente datos JSON gracias a las anotaciones de Jackson (@JsonProperty), lo que simplifica la conversión entre objetos Java y datos serializados.

En resumen, esta clase es fundamental para representar cada equipo como una entidad independiente dentro del sistema.

### Clase Liga

La clase **Liga** organiza los equipos en dos categorías: "La Liga" y la "Premier League". Su propósito es actuar como un contenedor para grupos de equipos.

- **Agrupar equipos:** Divide los equipos en dos listas según su liga.
- **Facilitar el acceso y manipulación:** Proporciona métodos para acceder y modificar los equipos de cada lista.
- **Serialización y deserialización de datos:** Permite que un archivo JSON estructurado pueda convertirse directamente en un objeto Liga, lo que simplifica la carga y almacenamiento de los datos.

Esta clase centraliza los equipos en un modelo jerárquico para que sean fácilmente gestionables en el contexto de la aplicación.

### Clase Main

La clase **Main** es el punto de entrada y coordina toda la lógica de la aplicación. Su propósito principal es generar contenido dinámico basado en datos de entrada y plantillas HTML.

- **Cargar configuración:** Lee el archivo config.ini para obtener el nombre del proyecto y el tema seleccionado.
- **Procesar datos de equipos:** Deserializa un archivo JSON que contiene los datos de los equipos organizados por liga.
- **Generar páginas web estáticas:** Usa Thymeleaf para procesar plantillas HTML con los datos de equipos y ligas.
- **Crea una página principal (index.html) y una página individual para cada equipo.**

En términos simples, esta clase automatiza la generación de una web dinámica basada en datos y plantillas, integrando todas las piezas del sistema (Equipo y Liga) para producir un resultado final tangible.

---

## 4. FICHEROS DE SALIDA

### 4.1 XML RSS

Es un formato utilizado para compartir información actualizada sobre los mejores equipos de fútbol de "La Liga" y la "Premier League". Su finalidad principal es permitir que aplicaciones, lectores de RSS o sitios web puedan mostrar automáticamente un resumen de esta información con enlaces y descripciones. Es ideal para mantener a los usuarios informados sin necesidad de visitar directamente el sitio web.

**Datos contenidos en el XML:**

- **Información general del canal:**
    - **Título:** "Equipos de Fútbol", describe de qué trata el contenido.
    - **Enlace principal:** Apunta a una página central que agrupa toda la información (el archivo index.html generado).
    - **Descripción:** Explica brevemente que el feed proporciona información sobre los mejores equipos de ambas ligas.

- **Lista de equipos:**
    - **Nombre del equipo:** Por ejemplo, "Real Madrid" o "Liverpool FC".
    - **Enlace individual:** Apunta a la página HTML específica del equipo, donde se puede encontrar información más detallada.
    - **Mejor jugador:** Describe quién es el jugador destacado del equipo.

---

### 4.2 CAPTURAS DE LAS PÁGINAS

![Screenshot_20241204_101009.png](capturas/Screenshot_20241204_101009.png)
![Screenshot_20241204_101035.png](capturas/Screenshot_20241204_101035.png)

---
