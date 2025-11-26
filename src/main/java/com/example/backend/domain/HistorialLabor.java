package com.example.backend.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "historial_labor")
public class HistorialLabor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long rutaId;
    private LocalDate fecha;
    private Integer puntosCompletados;
    private String observacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    public HistorialLabor() {}

    public HistorialLabor(Long rutaId, LocalDate fecha, Integer puntosCompletados, String observacion, Empleado empleado) {
        this.rutaId = rutaId;
        this.fecha = fecha;
        this.puntosCompletados = puntosCompletados;
        this.observacion = observacion;
        this.empleado = empleado;
    }

    public Long getId() {
        return id;
    }

    public Long getRutaId() {
        return rutaId;
    }

    public void setRutaId(Long rutaId) {
        this.rutaId = rutaId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getPuntosCompletados() {
        return puntosCompletados;
    }

    public void setPuntosCompletados(Integer puntosCompletados) {
        this.puntosCompletados = puntosCompletados;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
