package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class VehiculoRequest {
    @NotBlank
    private String marca;
    @NotBlank
    private String modelo;
    @NotBlank
    private String patente;
    @NotNull
    private Integer anio;
    @NotNull
    private Integer capacidad;

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
