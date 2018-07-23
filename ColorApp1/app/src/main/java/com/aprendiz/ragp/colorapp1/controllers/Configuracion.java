package com.aprendiz.ragp.colorapp1.controllers;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import com.aprendiz.ragp.colorapp1.R;

public class Configuracion extends AppCompatActivity {
    RadioButton rbtnTiempo, rbtnAciertos;
    EditText txtTiempo;
    SharedPreferences juegoC;
    int tiempo, modo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        inputValues();
        inputData();
    }


    private void inputValues() {
        juegoC = getSharedPreferences("juegoC", MODE_PRIVATE);
        modo = juegoC.getInt("modo",1);
        tiempo = juegoC.getInt("tiempo",3);

    }


    private void inputData() {
        if (modo==1){
            rbtnTiempo.setChecked(true);
        }

        if (modo==2){
            rbtnAciertos.setChecked(true);
        }

    }

    public void salir(View view) {
        
    }
}
