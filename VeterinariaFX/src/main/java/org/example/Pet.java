package org.example;

public class Pet {
    private String nome;
    private String especie;
    private String raca;

    public Pet(String nome, String especie, String raca) {
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public String getRaca() {
        return raca;
    }
}
