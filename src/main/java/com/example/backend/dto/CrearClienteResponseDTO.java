package com.example.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CrearClienteResponseDTO {

    @JsonProperty("idcliente")
    private Integer idCliente;
    private String nombreEmpresa;
    private String rut;
    private String direccion;
    private String telefono;
    private String correo;
    private String material;
    private String frecuencia;
    private String fotoUri;

    public CrearClienteResponseDTO(Integer idCliente, String nombreEmpresa, String rut, String direccion,
                                   String telefono, String correo, String material, String frecuencia, String fotoUri) {
        this.idCliente = idCliente;
        this.nombreEmpresa = nombreEmpresa;
        this.rut = rut;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.material = material;
        this.frecuencia = frecuencia;
        this.fotoUri = fotoUri;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getRut() {
        return rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getMaterial() {
        return material;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public String getFotoUri() {
        return fotoUri;
    }
}
