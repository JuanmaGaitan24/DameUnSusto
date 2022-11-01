package com.example.dameunsusto;

import static com.google.android.material.R.color.m3_ref_palette_white;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LinearLayout listaSpinner, listaNsegundos, listaplay;
    EditText etCantSustos;
    Button btnCrearSustos;
    ScrollView listaPrincipal;

    int NumeroDeSustos;

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

    int[] sonidos = new int[]{
        R.raw.aliensound, R.raw.leavesrustling,
        R.raw.glassbreaking, R.raw.chains,
        R.raw.chainsaw, R.raw.doorcreaks,
        R.raw.evillaugh, R.raw.womanfalling,
        R.raw.footsteps, R.raw.gutsbeingmangled,
        R.raw.heartbeat, R.raw.knifebeingsharpened,
        R.raw.knockdoor, R.raw.monsterroar,
        R.raw.song, R.raw.thunderandrain,
        R.raw.windandrain, R.raw.wolves,
        R.raw.creaks, R.raw.zombiescream
    };

    String[] nombresSonidos = new String[]{};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombresSonidos = new String[]{
                getString(R.string.alien), getString(R.string.arbusto),
                getString(R.string.cristalroto), getString(R.string.cadenas),
                getString(R.string.motosierra), getString(R.string.puertacerrandose),
                getString(R.string.risamalevola), getString(R.string.mujercallendo),
                getString(R.string.pasos), getString(R.string.tripas),
                getString(R.string.latidos), getString(R.string.cuchillo),
                getString(R.string.llamarpuerta), getString(R.string.rugidomonstruo),
                getString(R.string.cancion), getString(R.string.tormenta),
                getString(R.string.vientolluvia), getString(R.string.lovos),
                getString(R.string.maderacruguiendo), getString(R.string.zombie)
        };

        listaSpinner = findViewById(R.id.filaSpinner);
        listaNsegundos = findViewById(R.id.filaTextNumber);
        listaplay = findViewById(R.id.filacuentaatras);
        etCantSustos = findViewById(R.id.editTextNSustos);
        btnCrearSustos = findViewById(R.id.buttonCrearSustos);
        listaPrincipal = findViewById(R.id.listaSustos);

        btnCrearSustos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int numsustos = Integer.valueOf(String.valueOf(etCantSustos.getText()));

                NumeroDeSustos = numsustos;

                if (numsustos >= 1 && numsustos <= 10){

                    for (int i = 0; i < numsustos; i++){

                        Spinner listaSonidos = new Spinner(MainActivity.this);
                        EditText Nsegundos = new EditText(MainActivity.this);
                        Button btnCuentaAtras = new Button(MainActivity.this);

                        listaSonidos.setLayoutParams(new LinearLayout.LayoutParams(500, 250));
                        listaSonidos.setPadding(20,60,0,40);

                        Nsegundos.setLayoutParams(new LinearLayout.LayoutParams(325, 250));
                        Nsegundos.setInputType(2);
                        Nsegundos.setTextColor(getResources().getColor(android.R.color.holo_orange_dark));
                        Nsegundos.setHint(getString(R.string.numerosegundos));
                        Nsegundos.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        Nsegundos.setHintTextColor(getResources().getColor(android.R.color.holo_orange_light));
                        Nsegundos.setPadding(30,60,0,40);

                        btnCuentaAtras.setLayoutParams(new LinearLayout.LayoutParams(250, 250));
                        btnCuentaAtras.setBackground(getResources().getDrawable(R.drawable.play));

                        listaSpinner.addView(listaSonidos);
                        listaNsegundos.addView(Nsegundos);
                        listaplay.addView(btnCuentaAtras);


                        AdaptadorParaSustos adaptadorParaSustos = new AdaptadorParaSustos(MainActivity.this, R.layout.lista, nombresSonidos);
                        listaSonidos.setAdapter(adaptadorParaSustos);

                        btnCuentaAtras.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                int numSeg;

                                try {

                                    numSeg = Integer.valueOf(String.valueOf(Nsegundos.getText()));

                                    int tiempo = numSeg;

                                    int posSonido = listaSonidos.getSelectedItemPosition();

                                    MiCuentaAtras miCuentaAtras = new MiCuentaAtras(tiempo, posSonido, listaSonidos, listaSpinner,Nsegundos, listaNsegundos, btnCuentaAtras, listaplay);
                                    miCuentaAtras.execute();

                                    listaSonidos.setEnabled(false);
                                    listaNsegundos.setEnabled(false);
                                    listaplay.setEnabled(false);

                                }catch (Exception e){
                                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.segundosmal), Toast.LENGTH_LONG);
                                    toast.show();
                                }


                            }
                        });

                    }

                    btnCrearSustos.setEnabled(false);
                    etCantSustos.setEnabled(false);

                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.errorcantidad), Toast.LENGTH_LONG);
                    toast.show();
                }

            }
        });

    }

    class MiCuentaAtras extends AsyncTask<String, String,String> {

        int miTiempo;
        int miPosSonido;
        TextView miNsegundos;
        Spinner miListaSonidos;
        Button miBtnCuentaAtras;
        LinearLayout miListaSpinner;
        LinearLayout miListaNSegundos;
        LinearLayout miListaPlay;

        public MiCuentaAtras(int tiempo, int possonido,EditText nsegundos) {
            miTiempo = tiempo;
            miPosSonido = possonido;
            miNsegundos = nsegundos;
        }

        public MiCuentaAtras(int tiempo, int posSonido, EditText nsegundos, LinearLayout listaNsegundos) {

            miTiempo = tiempo;
            miPosSonido = posSonido;
            miNsegundos = nsegundos;
            miListaNSegundos = listaNsegundos;
        }

        public MiCuentaAtras(int tiempo, int posSonido, Spinner listaSonidos, LinearLayout listaSpinner, EditText nsegundos, LinearLayout listaNsegundos, Button btnCuentaAtras, LinearLayout listaplay) {

            miTiempo = tiempo;
            miPosSonido = posSonido;
            miListaSonidos = listaSonidos;
            miNsegundos = nsegundos;
            miBtnCuentaAtras = btnCuentaAtras;
            miListaSpinner = listaSpinner;
            miListaNSegundos = listaNsegundos;
            miListaPlay = listaplay;

        }

        @Override
        protected void onProgressUpdate(String... values) {
            miTiempo--;
            miNsegundos.setText(String.valueOf(miTiempo));
        }

        @Override
        protected void onPostExecute(String s) {

            MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, sonidos[miPosSonido]);
            mediaPlayer.start();
            miListaSpinner.removeView(miListaSonidos);
            miListaNSegundos.removeView(miNsegundos);
            miListaPlay.removeView(miBtnCuentaAtras);

            comprobarlista();

        }

        @Override
        protected String doInBackground(String... strings) {

            while (miTiempo > 0){

                publishProgress();

                try {
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

            }

            return null;
        }

    }

    private void comprobarlista() {

        NumeroDeSustos--;

        if(NumeroDeSustos == 0){

            btnCrearSustos.setEnabled(true);
            etCantSustos.setEnabled(true);

        }

    }

    private class AdaptadorParaSustos extends ArrayAdapter<String>{

        public AdaptadorParaSustos(@NonNull Context context, int resource, @NonNull String[] objects){
            super(context, resource, objects);
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return rellenarFila(position, convertView, parent);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return rellenarFila(position, convertView, parent);
        }

        private View rellenarFila(int position, View convertView, ViewGroup parent){

            LayoutInflater inflater = getLayoutInflater();
            View miFila = inflater.inflate(R.layout.lista, parent, false);

            TextView nombre = miFila.findViewById(R.id.textViewtitulo);
            ImageView icono = miFila.findViewById(R.id.imageViewIcono);

            nombre.setText(nombresSonidos[position]);
            icono.setImageResource(logos[position]);

            return miFila;
        }

    }

}