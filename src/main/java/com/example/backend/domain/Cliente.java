package com.example.backend.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Cliente() {}

    public Cliente(String nombreEmpresa, String rut, String direccion, String telefono, String correo,
                   String material, String frecuencia, String fotoUri, Long idChoferAsignado, Long idVehiculoAsignado) {
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

    public Long getId() {
        return id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getFotoUri() {
        return fotoUri;
    }

    public void setFotoUri(String fotoUri) {
        this.fotoUri = fotoUri;
    }

    public Long getIdChoferAsignado() {
        return idChoferAsignado;
    }

    public void setIdChoferAsignado(Long idChoferAsignado) {
        this.idChoferAsignado = idChoferAsignado;
    }

    public Long getIdVehiculoAsignado() {
        return idVehiculoAsignado;
    }

    public void setIdVehiculoAsignado(Long idVehiculoAsignado) {
        this.idVehiculoAsignado = idVehiculoAsignado;
    }
}
