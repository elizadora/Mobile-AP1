package com.example.todolist;

public class Item {
    String titulo;
    String descricao;

    Item(String titulo, String descricao){
        this.titulo = titulo;
        this.descricao = descricao;
    }

    // get
    public String getTitulo(){
        return this.titulo;
    }

    public String getDescricao(){
        return this.descricao;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
}
