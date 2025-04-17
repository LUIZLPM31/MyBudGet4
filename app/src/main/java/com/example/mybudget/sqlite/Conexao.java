package com.example.mybudget.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Conexao extends SQLiteOpenHelper{


    public static  SQLiteDatabase instance;


    public static SQLiteDatabase getInstance() {
        return instance;
    }

    public static void closeConnection() {
        if (instance != null && instance.isOpen()) {
            instance.close();
        }
    }

    public Conexao( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        instance = getWritableDatabase();
    }


    public void onCreate(SQLiteDatabase db) {

        String tabelaUsuario = "";
        tabelaUsuario += "CREATE TABLE IF NOT EXISTS usuario (";
        tabelaUsuario += "id INTEGER PRIMARY KEY AUTOINCREMENT,";
        tabelaUsuario += "nome VARCHAR(100) NOT NULL,";
        tabelaUsuario += "email VARCHAR(100) NOT NULL,";
        tabelaUsuario += "login VARCHAR(100) NOT NULL,";
        tabelaUsuario += "senha VARCHAR(100) NOT NULL";
        tabelaUsuario += ")";

        db.execSQL(tabelaUsuario);


    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
