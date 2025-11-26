package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class CrearMaterialRequestDTO {
    @NotBlank
    private String nombre;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
