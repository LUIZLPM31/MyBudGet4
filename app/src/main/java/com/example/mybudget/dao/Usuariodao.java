package com.example.mybudget.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mybudget.entity.Usuario;
import com.example.mybudget.sqlite.Conexao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class Usuariodao {

    public void salvar(Usuario usuario) {
        SQLiteDatabase conexao = Conexao.getInstance();
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());

        conexao.insert("usuario", null, values);
    }

    public void atualizar(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());

        SQLiteDatabase conexao = Conexao.getInstance();
        conexao.update("usuario", values, "id = ?", new String[]{String.valueOf(usuario.getId())});
    }

    @SuppressLint("Range")
    public List<Usuario> listar() {
        List<Usuario> usuarios = new ArrayList<>();
        SQLiteDatabase conexao = Conexao.getInstance();
        Cursor cursor = conexao.rawQuery("SELECT * FROM usuario", null);

        if (cursor.moveToFirst()){
           do {
            Usuario usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndex("id")));
            usuario.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            usuario.setLogin(cursor.getString(cursor.getColumnIndex("login")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndex("senha")));
            usuarios.add(usuario);
        } while (cursor.moveToNext());
        }

        return usuarios;
    }

    public void excluir(Usuario usuario) {
        SQLiteDatabase conexao = Conexao.getInstance();
        conexao.delete("usuario", "id = ?", new String[]{String.valueOf(usuario.getId())});
    }



}
