package com.example.backend.dto;

public class ClienteDTO {
    private String id;
    private String nombreEmpresa;
    private String rut;
    private String direccion;
    private String telefono;
    private String correo;
    private String material;
    private String frecuencia;
    private String fotoUri;
    private Long idChoferAsignado;
    private Long idVehiculoAsignado;

    public ClienteDTO(String id, String nombreEmpresa, String rut, String direccion, String telefono, String correo,
                      String material, String frecuencia, String fotoUri, Long idChoferAsignado, Long idVehiculoAsignado) {
        this.id = id;
        this.nombreEmpresa = nombreEmpresa;
        this.rut = rut;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.material = material;
        this.frecuencia = frecuencia;
        this.fotoUri = fotoUri;
        this.idChoferAsignado = idChoferAsignado;
        this.idVehiculoAsignado = idVehiculoAsignado;
    }

    public String getId() {
        return id;
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

    public Long getIdChoferAsignado() {
        return idChoferAsignado;
    }

    public Long getIdVehiculoAsignado() {
        return idVehiculoAsignado;
    }
}
