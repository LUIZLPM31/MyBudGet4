package com.example.mybudget.entity;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

public class Usuario {
    
    private Integer id;
    private String nome;
    private String email;
    private String login;
    private String senha;

    @NonNull
    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("USUARIO [id=%d," +
                        " NOME=%s," +
                        " E-MAIL=%s," +
                        " LOGIN=%s," +
                        " SENHA=%s]",
                id, nome, email, login, senha);
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
        }
    
    public String getNome() {
        return nome;
    }
    
    public String getEmail(){
        return email;
    }
    public String getLogin(){
        return login;
    }
    public String getSenha(){
        return senha;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
