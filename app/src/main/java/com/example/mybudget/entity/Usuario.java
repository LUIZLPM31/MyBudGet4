package com.example.mybudget.entity;

public class Usuario {
    
    private Integer id;
    private String nome;
    private String email;
    private String login;
    private String senha;

    @Override
    public String toString() {
        return String.format("Usuario [id=%d, nome=%s, email=%s, login=%s, senha=%s]",
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
