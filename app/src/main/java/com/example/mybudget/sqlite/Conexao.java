package com.example.mybudget.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class Conexao extends SQLiteOpenHelper{


    public static  SQLiteDatabase instance;
    private static Context context;


    public static SQLiteDatabase getInstance() {
        if (instance == null || !instance.isOpen()) {
            Conexao.context = context;
            instance = new Conexao(context, "mybudget.db", null, 1).getWritableDatabase();
        }
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
        try {
            String tabelaUsuario = "CREATE TABLE IF NOT EXISTS usuario (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nome VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) NOT NULL," +
                    "login VARCHAR(100) NOT NULL," +
                    "senha VARCHAR(100) NOT NULL)";
            db.execSQL(tabelaUsuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }
}
