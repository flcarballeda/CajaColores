package org.lopez.fernando.cajacolores;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class GetSquareActivity extends AppCompatActivity {

    public static final String INTENT_PARAMETER_SQUARES = "IntentParameterSquares";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_square);
        NumberPicker numbers = findViewById(R.id.numberSquares);

        numbers.setMinValue(30);
        numbers.setMaxValue(100);
        numbers.setValue(50);
    }

    public void onClick(View view) {
        NumberPicker numbers = findViewById(R.id.numberSquares);
        Intent intent = new Intent(this, SplitView.class);
        intent.putExtra(INTENT_PARAMETER_SQUARES, numbers.getValue());
        this.finish();
        this.startActivity(intent);
    }
}
