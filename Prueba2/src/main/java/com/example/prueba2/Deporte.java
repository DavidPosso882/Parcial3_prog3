package com.example.prueba2;

public class Deporte {
    private String id;
    private String nombre;
    private String dificultad;
    private String entrenadores;
    private String descripcion;

    public Deporte(String id, String nombre, String dificultad, String entrenadores, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.dificultad = dificultad;
        this.entrenadores = entrenadores;
        this.descripcion = descripcion;
    }

    // Getters y setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(String entrenadores) {
        this.entrenadores = entrenadores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}


