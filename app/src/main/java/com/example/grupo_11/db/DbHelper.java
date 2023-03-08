package com.example.grupo_11.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NOMBRE = "empleados.db";
    public static final String TABLE_EMPLEADOS = "empleados";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_EMPLEADOS + " (" + "" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DNI VARCHAR NOT NULL," +
                "Nombre VARCHAR NOT NULL," +
                "Apellidos VARCHAR NOT NULL," +
                "Telefono INT NOT NULL," +
                "Departamento VARCHAR NOT NULL," +
                "Localidad VARCHAR NOT NULL," +
                "Direccion VARCHAR NOT NULL," +
                "Email VARCHAR NOT NULL," +
                "Horario DATETIME NOT NULL)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE " + TABLE_EMPLEADOS);

        onCreate(sqLiteDatabase);
    }
}
