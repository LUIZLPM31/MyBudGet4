package com.example.mybudget.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mybudget.entity.Usuario;
import com.example.mybudget.sqlite.Conexao;

import java.util.ArrayList;
import java.util.List;

public class Usuariodao {

    public void salvar(Usuario usuario){
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());

        SQLiteDatabase conexao = Conexao.getInstance();
        conexao.insert("usuario", null, values);

    }

    public void atualizar(Usuario usuario){

    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase conexao = Conexao.getInstance();
        Cursor cursor = conexao.rawQuery("SELECT * FROM usuario", null);
        while (cursor.moveToNext()) {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            usuario.setLogin(cursor.getString(cursor.getColumnIndex("login")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            usuarios.add(usuario);
        }
        cursor.close();
        return usuarios;
    }

    public void excluir(Usuario usuario){

    }

}
