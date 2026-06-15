package com.alura.apifipe.model;

public class Veiculo {
    private String valor;
    private String marca;
    private String modelo;
    private int ano;
    private String combustivel;
    private String codigoFipe;

    public Veiculo(DTOVeiculos veiculos){
        this.valor = veiculos.valor();
        this.marca = veiculos.marca();
        this.modelo = veiculos.modelo();
        this.ano = veiculos.ano();
        this.combustivel = veiculos.combustivel();
        this.codigoFipe = veiculos.codigoFipe();
    }

    public String getValor() {
        return valor;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public String getCodigoFipe() {
        return codigoFipe;
    }

    @Override
    public String toString() {
        return this.marca + " " + this.modelo +
                " | ano: " + this.ano +
                " | valor: " + this.valor +
                " | combustível: " + this.combustivel +
                " | código fipe: " + this.codigoFipe;
    }
}
