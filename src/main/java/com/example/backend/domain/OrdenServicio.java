package com.example.backend.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordenes_servicio")
public class OrdenServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "punto_id")
    private PuntoRecoleccion punto;

    @Enumerated(EnumType.STRING)
    private OrdenEstado estado;

    @ElementCollection
    @CollectionTable(name = "orden_fotos", joinColumns = @JoinColumn(name = "orden_id"))
    @Column(name = "foto")
    private List<String> fotos = new ArrayList<>();

    private String observacion;

    public OrdenServicio() {}

    public OrdenServicio(Ruta ruta, PuntoRecoleccion punto, OrdenEstado estado) {
        this.ruta = ruta;
        this.punto = punto;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public PuntoRecoleccion getPunto() {
        return punto;
    }

    public void setPunto(PuntoRecoleccion punto) {
        this.punto = punto;
    }

    public OrdenEstado getEstado() {
        return estado;
    }

    public void setEstado(OrdenEstado estado) {
        this.estado = estado;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
