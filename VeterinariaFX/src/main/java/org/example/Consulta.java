package org.example;

public class Consulta {
    private Pet pet;
    private String data;
    private String motivo;

    public Consulta(Pet pet, String data, String motivo) {
        this.pet = pet;
        this.data = data;
        this.motivo = motivo;
    }

    public Pet getPet() {
        return pet;
    }

    public String getData() {
        return data;
    }

    public String getMotivo() {
        return motivo;
    }
}
