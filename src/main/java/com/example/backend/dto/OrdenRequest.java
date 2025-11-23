package com.example.backend.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrdenRequest {
    @NotNull
    private Long rutaId;
    @NotNull
    private Long puntoId;
    private String estado;
    private List<String> fotos;
    private String observacion;

    public Long getRutaId() {
        return rutaId;
    }

    public void setRutaId(Long rutaId) {
        this.rutaId = rutaId;
    }

    public Long getPuntoId() {
        return puntoId;
    }

    public void setPuntoId(Long puntoId) {
        this.puntoId = puntoId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
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
