package org.lopez.fernando.cajacolores;

import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PuntuacionJson {
    public static List<Puntuacion> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessagesArray(reader);
        } finally {
            reader.close();
        }
    }

    public static List<Puntuacion> readMessagesArray(JsonReader reader) throws IOException {
        List<Puntuacion> messages = new ArrayList<Puntuacion>();

        reader.beginArray();
        while (reader.hasNext()) {
            messages.add(readMessage(reader));
        }
        reader.endArray();
        return messages;
    }

    public static Puntuacion readMessage(JsonReader reader) throws IOException {
        String juego = "";
        String nombre = "";
        String fecha = "";
        long tiempo = -1;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("juego")) {
                juego = reader.nextString();
            } else if (name.equals("nombre")) {
                nombre = reader.nextString();
            } else if (name.equals("fecha")) {
                fecha = reader.nextString();
            } else if (name.equals("tiempo")) {
                tiempo = reader.nextLong();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Puntuacion(juego, nombre, fecha, tiempo);
    }

}
