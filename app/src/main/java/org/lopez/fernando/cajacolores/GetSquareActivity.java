package org.lopez.fernando.cajacolores;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class GetSquareActivity extends AppCompatActivity {
    private String nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_square);
        NumberPicker numbers = findViewById(R.id.numberSquares);

        numbers.setMinValue(30);
        numbers.setMaxValue(100);
        UserPreferences up = new UserPreferences(this);
        numbers.setValue(up.getSplitTokes());
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            nombre = bd.getString(Constantes.NOM_RECORD_SPLIT, null);
        }
    }

    public void onClick(View view) {
        NumberPicker numbers = findViewById(R.id.numberSquares);
        Intent intent = new Intent(this, SplitView.class);
        intent.putExtra(Constantes.INTENT_PARAMETER_SQUARES, numbers.getValue());
        intent.putExtra(Constantes.NOM_RECORD_SPLIT, nombre);
        UserPreferences up = new UserPreferences(this);
        up.setSplitTokes(numbers.getValue());
        this.finish();
        this.startActivity(intent);
    }
}
