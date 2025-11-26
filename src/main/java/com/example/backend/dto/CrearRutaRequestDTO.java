package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CrearRutaRequestDTO {
    @NotBlank
    private String nombre;
    @NotBlank
    private String fecha;
    @NotBlank
    private String estado;
    @NotNull
    private String idChoferAsignado;

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public String getIdChoferAsignado() {
        return idChoferAsignado;
    }
}
