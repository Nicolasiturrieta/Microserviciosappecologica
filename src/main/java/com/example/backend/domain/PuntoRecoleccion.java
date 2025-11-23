package com.example.backend.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "puntos_recoleccion")
public class PuntoRecoleccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String direccion;
    private String material;
    private String contenedor;
    private String observaciones;

    public PuntoRecoleccion() {}

    public PuntoRecoleccion(Cliente cliente, String direccion, String material, String contenedor, String observaciones) {
        this.cliente = cliente;
        this.direccion = direccion;
        this.material = material;
        this.contenedor = contenedor;
        this.observaciones = observaciones;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getContenedor() {
        return contenedor;
    }

    public void setContenedor(String contenedor) {
        this.contenedor = contenedor;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
