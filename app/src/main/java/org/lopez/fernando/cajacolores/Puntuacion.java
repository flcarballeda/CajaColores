package org.lopez.fernando.cajacolores;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Puntuacion {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private String juego = "";
    private String nombre = "";
    private String fecha = "";
    private long tiempo = -1;

    public Puntuacion(String juego, String nombre, String fecha, long tiempo) {
        this.juego = juego;
        this.nombre = nombre;
        this.fecha = fecha;
        this.tiempo = tiempo;
    }

    public Puntuacion(String juego, String nombre, Date fecha, long tiempo) {
        this.juego = juego;
        this.nombre = nombre;
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        this.fecha = format.format(fecha);
        this.tiempo = tiempo;
    }

    public String getJuego() {
        return juego;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public Date getDate() {
        SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
        try {
            return format.parse(fecha);
        } catch (ParseException ex) {
            return null;
        }
    }

    public long getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return "Puntuacion{" +
                "juego='" + juego + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fecha='" + fecha + '\'' +
                ", tiempo=" + tiempo +
                '}';
    }

    public String toJsonString() {
        // Ejemplo usando la librería de Google para manipular JSON.
        Gson gson = new Gson();
        return gson.toJson(this);
        /** Ejemplo hecho a mano por mí.
         return "{" +
         "\"juego\"=\"" + juego + '"' +
         ", \"nombre\"=\"" + nombre + '"' +
         ", \"fecha\"=\"" + fecha + '"' +
         ", \"tiempo\"=" + Long.toString(tiempo) + '}';
         */
    }

    /**
     *  [
     *    {
     *      "juego": "Split",
     *      "nombre": "ASDEF",
     *      "fecha": "",
     *      "tiempo": 912345678902
     *    },
     *    {
     *      "juego": "Split",
     *      "nombre": "How do I read JSON on Android?",
     *      "fecha": "",
     *      "tiempo": 912345678902
     *    },
     *    {
     *      "juego": "Colores",
     *      "nombre": "NANANANANAN",
     *      "fecha": "",
     *      "tiempo": 912345678902
     *    }
     *  ]
     */
}
