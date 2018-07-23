package com.aprendiz.ragp.colorapp1.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class GestorDB extends SQLiteOpenHelper{
    public GestorDB(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VALUE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Constants.script);
    }


    public List<Score> scoreList (){
        List<Score> results = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM SCORE ORDER BY PUNTAJE DESC, INCORRECTAS DESC; ", null);
        if (cursor.moveToFirst()){
            do {
                Score score = new Score();
                score.setPuntaje(cursor.getString(0));
                score.setIncorrectas(cursor.getString(1));

                results.add(score);


            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return results;
    }

    public void inputData(Score score){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("PUNTAJE", Integer.parseInt(score.getPuntaje()));
        values.put("INCORRECTAS", Integer.parseInt(score.getIncorrectas()));
        db.insert("SCORE",null, values);
        db.close();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
