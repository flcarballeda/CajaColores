package org.lopez.fernando.cajacolores;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPreferences {
    private SharedPreferences preferences;

    public UserPreferences(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    public void setCajacoloresRecord(String nombre, long tiempo) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constantes.NOM_RECORD_CAJACOLORES, nombre);
        editor.putLong(Constantes.DATA_RECORD_CAJACOLORES, tiempo);
        editor.commit();
    }

    public void setSplitRecord(String nombre, long tiempo) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constantes.NOM_RECORD_SPLIT, nombre);
        editor.putLong(Constantes.DATA_RECORD_SPLIT, tiempo);
        editor.commit();
    }

    public void setSplitTokes(int tokes) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(Constantes.INTENT_PARAMETER_SQUARES, tokes);
        editor.commit();
    }

    public int getSplitTokes() {
        return preferences.getInt(Constantes.INTENT_PARAMETER_SQUARES, 50);
    }

    public Datos getCajacoloresRecord() {
        String nombre = preferences.getString(Constantes.NOM_RECORD_CAJACOLORES, null);
        if (null == nombre) {
            return null;
        }
        return new Datos(nombre, preferences.getLong(Constantes.DATA_RECORD_CAJACOLORES, -1l));
    }

    public Datos getSplitRecord() {
        String nombre = preferences.getString(Constantes.NOM_RECORD_SPLIT, null);
        if (null == nombre) {
            return null;
        }
        return new Datos(nombre, preferences.getLong(Constantes.DATA_RECORD_SPLIT, -1l));
    }

    public class Datos {
        private String nombre;
        private long time;

        Datos(String nombre, long tiempo) {
            this.nombre = nombre;
            this.time = tiempo;
        }

        String getNombre() {
            return this.nombre;
        }

        long getTime() {
            return this.time;
        }
    }
}
