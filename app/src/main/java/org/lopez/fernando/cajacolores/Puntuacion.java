package org.lopez.fernando.cajacolores;

public class Puntuacion {
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

    public String getJuego() {
        return juego;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
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
        return "{" +
                "\"juego\"=\"" + juego + '"' +
                ", \"nombre\"=\"" + nombre + '"' +
                ", \"fecha\"=\"" + fecha + '"' +
                ", \"tiempo\"=" + Long.toString(tiempo) + '}';
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
