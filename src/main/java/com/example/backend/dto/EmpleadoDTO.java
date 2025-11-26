package com.example.backend.dto;

public class EmpleadoDTO {
    private Long id;
    private String nombre;
    private String rut;
    private String correo;
    private String telefono;
    private String rol;
    private String fotoUri;

    public EmpleadoDTO(Long id, String nombre, String rut, String correo, String telefono, String rol, String fotoUri) {
        this.id = id;
        this.nombre = nombre;
        this.rut = rut;
        this.correo = correo;
        this.telefono = telefono;
        this.rol = rol;
        this.fotoUri = fotoUri;
    }

    public Long getId() {
        return id;
    }

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
