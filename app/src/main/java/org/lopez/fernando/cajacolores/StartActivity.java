package org.lopez.fernando.cajacolores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * 1 Hacer una actividad inicial para que con un SPINNER para que se elija la modalidad que quieres jugar
 * 2 Crear una actividad previa a la modalidad "FER" para seleccionar el número de toques antes de entrar. Usar un NumberPicker
 * 3 Hacer un botón de pausa para que el juego se detenga y en ese momento no se pueda seguir (salga una pantalla botón, o imagen superpuesta o cualquier tipo de aviso) y que permita reanudar la partida cuando el usuario vuelva a darle
 * 4 Definir una opción en el menú para que se pueda reiniciar la partida (y con ello el tiempo)
 *
 * EXTRA: PENSAR SOBRE LA APP DEL REGISTRO DE HORAS DE TRABAJO. Definir un nombre, REQUISITOS logo...
 */
public class StartActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        spinner = (Spinner) findViewById(R.id.spinnerGame);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinnerOptions, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener( this);
        /**
         * Ejemplo del uso de Gson para procesar el json de la listas de partidas o puntuaciones.
         Gson gson = new Gson();
         String json_data = "[{ \"juego\"=\"SPLIT\",\"nombre\"=\"carby\",\"fecha\"=\"2019-05-29\",\"tiempo\"=2356},{ \"juego\"=\"CAJACOLORES\",\"nombre\"=\"carby\",\"fecha\"=\"2019-05-29\",\"tiempo\"=22356}]";
         ArrayList<Puntuacion> resultados = gson.fromJson(json_data, ArrayList.class);
         Log.d("MYAPP", Integer.toString( resultados.size()));
         */
    }

    public void launchGame( View view) {
        EditText editText = findViewById(R.id.editTextNombreJugador);
        if (editText.getText().toString().trim().isEmpty()) {
            return;
        }
        int pos = spinner.getSelectedItemPosition();
        switch( pos) {
            case 0 : { // "Dividir"
                Intent intent = new Intent(this, GetSquareActivity.class);
                intent.putExtra(Constantes.NOM_RECORD_SPLIT, editText.getText().toString());
                startActivity(intent);
            } break;
            case 1 : { // "Colores"
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra(Constantes.NOM_RECORD_CAJACOLORES, editText.getText().toString());
                startActivity(intent);
            } break;
            default: { // Upss no se porque pasa por aquí
                Log.d("MIAPP", "Seleccionado " + pos);
            } break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        refrescaRecord(pos);
    }

    public void refrescaRecord(int pos) {
        UserPreferences up = new UserPreferences(this);
        UserPreferences.Datos datos;
        switch (pos) {
            case 0: {
                datos = up.getSplitRecord();
            }
            break;
            case 1: {
                datos = up.getCajacoloresRecord();
            }
            break;
            default: {
                return;
            } // break;
        }
        if (null == datos) {
            return;
        }
        String time = String.format(getResources().getString(R.string.messageShortTime), ((datos.getTime()) / 1000f));
        TextView nombre = findViewById(R.id.labelNombre);
        nombre.setText(datos.getNombre());
        TextView tiempo = findViewById(R.id.labelRecord);
        tiempo.setText(time);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        int pos = spinner.getSelectedItemPosition();
        refrescaRecord(pos);
    }
}
