package com.example.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmpleadoRequest {

    @NotBlank
    private String nombre;
    @NotBlank
    private String rut;
    @Email
    private String correo;
    @NotBlank
    private String telefono;
    @NotBlank
    private String rol;
    private String fotoUri;

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getRol() {
        return rol;
    }

    public String getFotoUri() {
        return fotoUri;
    }
}
