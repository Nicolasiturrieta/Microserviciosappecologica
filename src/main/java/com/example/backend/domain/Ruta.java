package com.example.backend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rutas")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private LocalDate fecha;
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chofer_id")
    private Empleado chofer;

    @ManyToMany
    @JoinTable(
            name = "ruta_puntos",
            joinColumns = @JoinColumn(name = "ruta_id"),
            inverseJoinColumns = @JoinColumn(name = "punto_id")
    )
    private List<PuntoRecoleccion> puntos = new ArrayList<>();

    public Ruta() {}

    public Ruta(String nombre, LocalDate fecha, String estado, Empleado chofer) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.estado = estado;
        this.chofer = chofer;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empleado getChofer() {
        return chofer;
    }

    public void setChofer(Empleado chofer) {
        this.chofer = chofer;
    }

    public List<PuntoRecoleccion> getPuntos() {
        return puntos;
    }

    public void setPuntos(List<PuntoRecoleccion> puntos) {
        this.puntos = puntos;
    }
}
