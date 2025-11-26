package com.example.backend.dto;

import java.time.LocalDate;

public class HistorialLaborDTO {
    private String rutaId;
    private LocalDate fecha;
    private Integer puntosCompletados;
    private String observacion;
    private Integer userId;

    public HistorialLaborDTO(String rutaId, LocalDate fecha, Integer puntosCompletados, String observacion, Integer userId) {
        this.rutaId = rutaId;
        this.fecha = fecha;
        this.puntosCompletados = puntosCompletados;
        this.observacion = observacion;
        this.userId = userId;
    }

    public String getRutaId() {
        return rutaId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Integer getPuntosCompletados() {
        return puntosCompletados;
    }

    public String getObservacion() {
        return observacion;
    }

    public Integer getUserId() {
        return userId;
    }
}
