package com.aprendiz.ragp.colorapp1.controllers;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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

        txtTiempo.setText(Integer.toString(tiempo));

    }

    public void salir(View view) {
        savePreferences(view);

        finish();
    }

    private void savePreferences(View view) {
        SharedPreferences.Editor  editor = juegoC.edit();
        if (rbtnTiempo.isChecked()){
            editor.putInt("modo",1);
        }

        if (rbtnAciertos.isChecked()){
            editor.putInt("modo",2);
        }

        try {
            int tiempo = Integer.parseInt(txtTiempo.getText().toString());
            if (tiempo>0 && tiempo<11){
                editor.putInt("modo",tiempo);
                editor.commit();
                Intent intent = new Intent(Configuracion.this, JuegoC.class);
                startActivity(intent);
            }else {
                Snackbar.make(view,"Por favor escriba un valor mayor a 0 y menor a 11",Snackbar.LENGTH_SHORT);
            }
        }catch (Exception e){
            Snackbar.make(view,"Por favor escriba un valor mayor a 0 y menor a 11",Snackbar.LENGTH_SHORT);
        }


    }
}
