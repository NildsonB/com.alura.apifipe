package com.alura.apifipe.model;

import java.util.List;

public class Dados {
    private String codigo;
    private String nome;

    public Dados(DTODados dados){
        this.codigo = dados.codigo();
        this.nome = dados.nome();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "codigo= " + this.codigo +
                " | nome= " + this.nome;
    }
}
