package org.lopez.fernando.cajacolores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
    }

    public void launchGame( View view) {
        int pos = spinner.getSelectedItemPosition();
        switch( pos) {
            case 0 : { // "Dividir"
                Intent intent = new Intent(this, SplitView.class);
                this.finish();
                startActivity(intent);
            } break;
            case 1 : { // "Colores"
                Intent intent = new Intent(this, MainActivity.class);
                this.finish();
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
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
