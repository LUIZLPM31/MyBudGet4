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
        SQLiteDatabase conexao = Conexao.getInstance();
        ContentValues values = new ContentValues();
        values.put("nome", usuario.getNome());
        values.put("email", usuario.getEmail());
        values.put("login", usuario.getLogin());
        values.put("senha", usuario.getSenha());

        // Atualiza o registro no banco de dados com base no ID do usuário
        int rowsAffected = conexao.update("usuario", values, "id = ?", new String[]{String.valueOf(usuario.getId())});

        if (rowsAffected == 0) {
            throw new RuntimeException("Erro ao atualizar o usuário. Nenhuma linha foi afetada.");
        }
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

    @Test
public void testAtualizarUsuario() {
    Usuariodao dao = new Usuariodao();

    // Cria um novo usuário
    Usuario usuario = new Usuario();
    usuario.setNome("Teste");
    usuario.setEmail("teste@email.com");
    usuario.setLogin("teste");
    usuario.setSenha("1234");

    // Salva o usuário no banco de dados
    dao.salvar(usuario);

    // Atualiza os dados do usuário
    usuario.setNome("Teste Atualizado");
    usuario.setEmail("atualizado@email.com");
    dao.atualizar(usuario);

    // Verifica se os dados foram atualizados
    List<Usuario> usuarios = dao.listar();
    boolean atualizado = false;
    for (Usuario u : usuarios) {
        if (u.getId().equals(usuario.getId()) && u.getNome().equals("Teste Atualizado")) {
            atualizado = true;
            break;
        }
    }

    assertTrue("O usuário não foi atualizado corretamente.", atualizado);
}
}
