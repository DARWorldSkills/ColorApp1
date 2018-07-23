package com.aprendiz.ragp.colorapp1.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aprendiz.ragp.colorapp1.R;

public class MenuJ extends AppCompatActivity implements View.OnClickListener{
    Button btnJugar, btnPuntuacion, btnConfiguracion;
    public static int guardar =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_j);
        inizialite();
        guardar =0;
    }

    private void inizialite() {
        btnJugar = findViewById(R.id.btnJugar);
        btnPuntuacion = findViewById(R.id.btnPuntuacion);
        btnConfiguracion = findViewById(R.id.btnConfiguracion);

        btnJugar.setOnClickListener(this);
        btnPuntuacion.setOnClickListener(this);
        btnConfiguracion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btnJugar:
                intent = new Intent(MenuJ.this, Juego.class);
                startActivity(intent);
                guardar =1;
                break;

            case R.id.btnPuntuacion:
                intent = new Intent(MenuJ.this, Puntaje.class);
                startActivity(intent);
                guardar =0;
                break;


            case R.id.btnConfiguracion:
                intent = new Intent(MenuJ.this, Configuracion.class);
                startActivity(intent);
                guardar =0;
                break;
        }
    }
}
