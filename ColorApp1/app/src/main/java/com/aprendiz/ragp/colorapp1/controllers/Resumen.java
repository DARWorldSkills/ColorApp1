package com.aprendiz.ragp.colorapp1.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aprendiz.ragp.colorapp1.R;
import com.aprendiz.ragp.colorapp1.models.GestorDB;
import com.aprendiz.ragp.colorapp1.models.Score;

public class Resumen extends AppCompatActivity {
    TextView txtCorrectas, txtIncorrectas, txtAciertos;
    Score score;
    int guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);
        inizialite();
        guardar= MenuJ.guardar;
        inputData();
    }

    private void inizialite() {
        txtCorrectas = findViewById(R.id.txtCorrectasR);
        txtIncorrectas = findViewById(R.id.txtIncorrectasR);
        txtAciertos = findViewById(R.id.txtAciertosR);


    }

    private void inputData() {
        if (guardar==1){
            txtCorrectas.setText(Integer.toString(Juego.correctas));
            txtIncorrectas.setText(Integer.toString(Juego.incorrectas));
            txtAciertos.setText(Integer.toString(Juego.aciertos)+"%");
            saveDataBase();
        }else {
            txtCorrectas.setText(Integer.toString(JuegoC.correctas));
            txtIncorrectas.setText(Integer.toString(JuegoC.incorrectas));
            txtAciertos.setText(Integer.toString(JuegoC.aciertos)+"%");
        }
    }

    private void saveDataBase() {
        score = new Score();
        score.setPuntaje(Integer.toString(Juego.aciertos));
        score.setIncorrectas(Integer.toString(Juego.incorrectas));
        GestorDB gestorDB = new GestorDB(this);
        gestorDB.inputData(score);
    }
}
