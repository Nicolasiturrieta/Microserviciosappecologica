package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class RutaRequest {
    @NotBlank
    private String nombre;
    @NotNull
    private LocalDate fecha;
    @NotBlank
    private String estado;
    @NotNull
    private Long choferId;
    private List<Long> puntoIds;

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

    public Long getChoferId() {
        return choferId;
    }

    public void setChoferId(Long choferId) {
        this.choferId = choferId;
    }

    public List<Long> getPuntoIds() {
        return puntoIds;
    }

    public void setPuntoIds(List<Long> puntoIds) {
        this.puntoIds = puntoIds;
    }
}
