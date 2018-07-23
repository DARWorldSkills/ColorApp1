package com.aprendiz.ragp.colorapp1.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aprendiz.ragp.colorapp1.R;
import com.aprendiz.ragp.colorapp1.models.GestorDB;
import com.aprendiz.ragp.colorapp1.models.Score;

import java.util.List;

public class Puntaje extends AppCompatActivity {
    TextView txtPrimero, txtSegundo, txtTercero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntaje);
        inizialite();
        inputData();
    }

    private void inizialite() {
        txtPrimero = findViewById(R.id.txtPrimero);
        txtSegundo = findViewById(R.id.txtSegundo);
        txtTercero = findViewById(R.id.txtTercero);

    }

    private void inputData() {
        GestorDB gestorDB = new GestorDB(this);
        List<Score> scoreList = gestorDB.scoreList();
        if (scoreList.size()>0){
            txtPrimero.setText(scoreList.get(0)+"%");
        }

        if (scoreList.size()>1){
            txtPrimero.setText(scoreList.get(1)+"%");
        }

        if (scoreList.size()>2){
            txtPrimero.setText(scoreList.get(2)+"%");
        }
    }

    public void salir(View view) {
        finish();
    }
}
