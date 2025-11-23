package com.example.backend.dto;

public class UsuarioDTO {
    private String id;
    private String nombre;
    private String rut;
    private String telefono;
    private String correo;
    private String rol;

    public UsuarioDTO(String id, String nombre, String rut, String telefono, String correo, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.telefono = telefono;
        this.correo = correo;
        this.rol = rol;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }
}
