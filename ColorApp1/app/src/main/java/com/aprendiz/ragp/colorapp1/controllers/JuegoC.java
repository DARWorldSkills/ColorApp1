package com.aprendiz.ragp.colorapp1.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aprendiz.ragp.colorapp1.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JuegoC extends AppCompatActivity implements View.OnClickListener{
    TextView txtCorrectas, txtIncorrectas, txtAciertos, txtIntentos, txtPalabra;
    Button btnColor1, btnColor2, btnColor3, btnColor4;
    ProgressBar tiempo;
    public static int correctas, incorrectas, aciertos, intentos;
    int [] segundos={0, 30};
    List<String> listaPalabras= new ArrayList<>();
    List<Integer> listaColores = new ArrayList<>();
    List<Integer> listaColoresTmp = new ArrayList<>();
    int ipR, icR, valorcito;
    boolean bandera = true;
    int ab =0;
    SharedPreferences juegoC;
    int mTiempo, modo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego_c);
        inizialite();
        cleanV();
        inputLists();
        randomizar();
        inputData();
        cronometro();
        if (modo==1){
            segundos[1]=30;
        }else {
            segundos[1]=0;
        }
        tiempo.setProgress(segundos[1]);

    }

    private void cronometro() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (bandera){
                    try{
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            segundos[0]++;
                            if (modo==1){
                                segundos[1]--;
                            }else {
                                segundos[1]++;
                            }


                            if (segundos[0]==mTiempo){
                                segundos[0]=0;
                                intentos++;
                                incorrectas++;
                                randomizar();
                                inputData();
                            }
                            endGame();
                            tiempo.setProgress(segundos[1]);

                        }
                    });
                }
            }
        });
        thread.start();
    }

    private void endGame() {
        if ((incorrectas==3 || segundos[1]==0) && ab==0  && modo==1){
            ab=1;
            inputData();
            bandera=false;
            Intent intent = new Intent(JuegoC.this, Resumen.class);
            startActivity(intent);
            finish();

        }

        if ((incorrectas==3) && ab==0  && modo==2){
            ab=1;
            inputData();
            bandera=false;
            Intent intent = new Intent(JuegoC.this, Resumen.class);
            startActivity(intent);
            finish();

        }
    }

    //Método para randmizar la el texto, color del texto y los botones
    private void randomizar() {
        listaColoresTmp= listaColores;
        Collections.shuffle(listaColoresTmp);
        ipR = (int) (Math.random() * 4);
        icR = (int) (Math.random() * 4);
        txtPalabra.setText(listaPalabras.get(ipR));
        txtPalabra.setTextColor(listaColores.get(icR));

        btnColor1.setBackgroundColor(listaColoresTmp.get(0));
        btnColor2.setBackgroundColor(listaColoresTmp.get(1));
        btnColor3.setBackgroundColor(listaColoresTmp.get(2));
        btnColor4.setBackgroundColor(listaColoresTmp.get(3));

    }

    //Listar las palabras y colores
    private void inputLists() {
        listaPalabras = new ArrayList<>();
        listaColores = new ArrayList<>();
        listaPalabras.add("AMARILLO");
        listaColores.add(getColor(R.color.AmarilloJ));
        listaPalabras.add("AZUL");
        listaColores.add(getColor(R.color.AzulJ));
        listaPalabras.add("ROJO");
        listaColores.add(getColor(R.color.RojoJ));
        listaPalabras.add("VERDE");
        listaColores.add(getColor(R.color.VerdeJ));

    }

    //Limpieza de variables
    private void cleanV() {
        correctas = 0;
        incorrectas = 0;
        aciertos = 0;
        intentos = 0;
        segundos = new int[]{0,30};
        bandera= true;
        ab =0;
        juegoC = getSharedPreferences("juegoC",MODE_PRIVATE);
        mTiempo = juegoC.getInt("tiempo",3);
        modo = juegoC.getInt("modo",1);

    }

    //Método para inicializar los campos de la vista necesarios
    private void inizialite() {
        txtCorrectas = findViewById(R.id.txtCorrectasJ);
        txtIncorrectas = findViewById(R.id.txtIncorrectasJ);
        txtAciertos = findViewById(R.id.txtAciertosJ);
        txtIntentos = findViewById(R.id.txtIntentossJ);
        txtPalabra = findViewById(R.id.txtPalabraJ);

        btnColor1 = findViewById(R.id.btnColor1);
        btnColor2 = findViewById(R.id.btnColor2);
        btnColor3 = findViewById(R.id.btnColor3);
        btnColor4 = findViewById(R.id.btnColor4);

        tiempo = findViewById(R.id.progressBarTime);

        btnColor1.setOnClickListener(this);
        btnColor2.setOnClickListener(this);
        btnColor3.setOnClickListener(this);
        btnColor4.setOnClickListener(this);

        tiempo.setMax(30);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btnColor1:
                valorcito =1;
                validar();
                break;

            case  R.id.btnColor2:
                valorcito =2;
                validar();
                break;


            case  R.id.btnColor3:
                valorcito =3;
                validar();
                break;

            case  R.id.btnColor4:
                valorcito =4;
                validar();
                break;
        }
    }


    // Validar el intento
    private void validar() {
        if (valorcito==1){
            if (icR==0){
                correctas++;
            }else {
                incorrectas++;
            }
        }

        if (valorcito==2){
            if (icR==1){
                correctas++;
            }else {
                incorrectas++;
            }
        }

        if (valorcito==3){
            if (icR==2){
                correctas++;
            }else {
                incorrectas++;
            }
        }

        if (valorcito==4){
            if (icR==3){
                correctas++;
            }else {
                incorrectas++;
            }
        }

        intentos++;
        segundos[0] = 0;
        randomizar();
        inputData();

    }

    //Método para ingresar en los campos txtCorrectas, txtIncorrectas, txtAciertos y txtIntentos
    private void inputData() {
        if (intentos>0){
            if (correctas>0){
                float tmp1 = correctas, tmp2 =intentos;
                double tmpP = (tmp1/ tmp2) * 100;
                aciertos = (int) tmpP;


            }else {
                aciertos=0;
            }

        }else {
            aciertos=100;
        }



        txtCorrectas.setText(Integer.toString(correctas));
        txtIncorrectas.setText(Integer.toString(incorrectas));
        txtAciertos.setText(Integer.toString(aciertos)+"%");
        txtIntentos.setText(Integer.toString(intentos));
    }

    @Override
    protected void onPause() {
        super.onPause();
        bandera=false;
        ab=0;
    }

    @Override
    protected void onStop() {
        super.onStop();
        bandera=false;
        ab=0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bandera=false;
        ab=0;
    }
}
