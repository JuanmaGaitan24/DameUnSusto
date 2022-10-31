package com.example.dameunsusto;

import static com.google.android.material.R.color.m3_ref_palette_white;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    int[] logos = new int[]{
        R.drawable.alien, R.drawable.arbusto,
        R.drawable.brokenglass, R.drawable.cadenas,
        R.drawable.chainsaw, R.drawable.doorclosing,
        R.drawable.evillaught, R.drawable.fallingwarning,
        R.drawable.footsteps, R.drawable.guts,
        R.drawable.heartbeat, R.drawable.knife,
        R.drawable.knock, R.drawable.monstruo,
        R.drawable.song, R.drawable.storm,
        R.drawable.wind, R.drawable.wolf,
        R.drawable.woodcreaks, R.drawable.zombie
    };

    String[] nombresSonidos = new String[]{
        String.valueOf(R.string.alien), String.valueOf(R.string.arbusto),
        String.valueOf(R.string.cristalroto), String.valueOf(R.string.cadenas),
        String.valueOf(R.string.motosierra), String.valueOf(R.string.puertacerrandose),
        String.valueOf(R.string.risamalevola), String.valueOf(R.string.mujercallendo),
        String.valueOf(R.string.pasos), String.valueOf(R.string.tripas),
        String.valueOf(R.string.latidos), String.valueOf(R.string.cuchillo),
        String.valueOf(R.string.llamarpuerta), String.valueOf(R.string.rugidomonstruo),
        String.valueOf(R.string.cancion), String.valueOf(R.string.tormenta),
        String.valueOf(R.string.vientolluvia), String.valueOf(R.string.lovos),
        String.valueOf(R.string.maderacruguiendo), String.valueOf(R.string.zombie)
    };

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
                        EditText Nsegundos = new EditText(MainActivity.this);
                        Button btnCuentaAtras = new Button(MainActivity.this);

                        listaSonidos.setLayoutParams(new LinearLayout.LayoutParams(400, 250));
                        listaSonidos.setPadding(20,60,0,40);

                        Nsegundos.setLayoutParams(new LinearLayout.LayoutParams(350, 250));
                        Nsegundos.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
                        Nsegundos.setHint(getString(R.string.numerosegundos));
                        Nsegundos.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        Nsegundos.setHintTextColor(getResources().getColor(android.R.color.holo_orange_light));
                        Nsegundos.setPadding(30,60,0,40);

                        btnCuentaAtras.setLayoutParams(new LinearLayout.LayoutParams(300, 250));
                        btnCuentaAtras.setBackground(getResources().getDrawable(R.drawable.play));

                        listaSpinner.addView(listaSonidos);
                        listaNsegundos.addView(Nsegundos);
                        listaplay.addView(btnCuentaAtras);

                        ArrayAdapter<String> adaptador;
                        adaptador = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, nombresSonidos);


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