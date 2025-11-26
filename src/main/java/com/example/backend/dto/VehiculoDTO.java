package com.example.backend.dto;

public class VehiculoDTO {
    private Long id;
    private String marca;
    private String modelo;
    private String patente;
    private Integer anio;
    private Integer capacidad;

    public VehiculoDTO(Long id, String marca, String modelo, String patente, Integer anio, Integer capacidad) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.patente = patente;
        this.anio = anio;
        this.capacidad = capacidad;
    }

    public Long getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public String getPatente() {
        return patente;
    }

    public Integer getAnio() {
        return anio;
    }

    public Integer getCapacidad() {
        return capacidad;
    }
}
