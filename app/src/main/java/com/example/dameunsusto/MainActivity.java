package com.example.dameunsusto;

import static com.google.android.material.R.color.m3_ref_palette_white;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout listaSpinner, listaNsegundos, listaplay;
    EditText etCantSustos;
    Button btnCrearSustos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSpinner = findViewById(R.id.filaSpinner);
        listaNsegundos = findViewById(R.id.filaTextNumber);
        listaplay = findViewById(R.id.filacuentaatras);
        etCantSustos = findViewById(R.id.editTextNSustos);
        btnCrearSustos = findViewById(R.id.buttonCrearSustos);

        btnCrearSustos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int numsustos = Integer.valueOf(String.valueOf(etCantSustos.getText()));

                if (numsustos >= 1 && numsustos <= 10){

                    for (int i = 0; i < numsustos; i++){

                        Spinner listaSonidos = new Spinner(MainActivity.this);
                        TextView Nsegundos = new TextView(MainActivity.this);
                        Button btnCuentaAtras = new Button(MainActivity.this);

                        listaSonidos.setLayoutParams(new LinearLayout.LayoutParams(150, 70));
                        listaSonidos.setPadding(20,40,0,40);

                        Nsegundos.setLayoutParams(new LinearLayout.LayoutParams(125, 70));
                        Nsegundos.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
                        Nsegundos.setHint(getString(R.string.numerosegundos));
                        Nsegundos.setHintTextColor(getResources().getColor(android.R.color.holo_orange_light));
                        Nsegundos.setPadding(30,40,0,40);

                        btnCuentaAtras.setLayoutParams(new LinearLayout.LayoutParams(100, 70));
                        btnCuentaAtras.setBackground(getResources().getDrawable(R.drawable.play));

                        listaSpinner.addView(listaSonidos);
                        listaNsegundos.addView(Nsegundos);
                        listaplay.addView(btnCuentaAtras);

                    }

                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.errorcantidad), Toast.LENGTH_LONG);
                    toast.show();
                }


            }
        });

    }
}