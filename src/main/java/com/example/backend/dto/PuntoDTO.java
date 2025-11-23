package com.example.backend.dto;

public class PuntoDTO {
    private String id;
    private String clienteId;
    private String direccion;
    private String material;
    private String contenedor;
    private String observaciones;

    public PuntoDTO(String id, String clienteId, String direccion, String material, String contenedor, String observaciones) {
        this.id = id;
        this.clienteId = clienteId;
        this.direccion = direccion;
        this.material = material;
        this.contenedor = contenedor;
        this.observaciones = observaciones;
    }

    public String getId() {
        return id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getMaterial() {
        return material;
    }

    public String getContenedor() {
        return contenedor;
    }

    public String getObservaciones() {
        return observaciones;
    }
}
