package org.example;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String telefone;
    private ArrayList<Pet> pets;

    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
        this.pets = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void adicionarPet(Pet pet) {
        pets.add(pet);
    }

    public String listarPets() {
        if (pets.isEmpty()) return "Nenhum";
        return String.join(", ", pets.stream().map(Pet::getNome).toList());
    }
}
