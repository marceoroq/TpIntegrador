package com.example.tpintegrador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MiDBOpenHelper extends SQLiteOpenHelper {

    // Database Info
    private static final String DATABASE_NAME = "alojamientoDB";
    private static final int DATABASE_VERSION = 1;

    public MiDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE ALOJAMIENTO (" +
                "_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE TEXT," +
                "DESCRIPCION TEXT," +
                "PRECIO REAL," +
                "HAS_INTERNET INTEGER," +
                "ALLOW_PETS INTEGER," +
                "TIPO TEXT," +
                "CAPACIDAD INTEGER," +
                "CALIFICACION REAL," +
                "COOR_LAT REAL," +
                "COOR_LON REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
