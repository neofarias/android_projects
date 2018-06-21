package com.example.marciosantos.appsqlite;

public class LivrariaEntidade {
    private int id;
    private String nome;
    private String evento;
    private String endereco;

    public LivrariaEntidade(int id, String nome, String evento, String endereco) {
        this.id = id;
        this.nome = nome;
        this.evento = evento;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
