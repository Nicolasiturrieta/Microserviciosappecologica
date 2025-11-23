package com.example.backend.dto;

import java.util.List;

public class OrdenServicioDTO {
    private String id;
    private String rutaId;
    private String puntoId;
    private String estado;
    private List<String> fotos;
    private String observacion;

    public OrdenServicioDTO(String id, String rutaId, String puntoId, String estado, List<String> fotos, String observacion) {
        this.id = id;
        this.rutaId = rutaId;
        this.puntoId = puntoId;
        this.estado = estado;
        this.fotos = fotos;
        this.observacion = observacion;
    }

    public String getId() {
        return id;
    }

    public String getRutaId() {
        return rutaId;
    }

    public String getPuntoId() {
        return puntoId;
    }

    public String getEstado() {
        return estado;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public String getObservacion() {
        return observacion;
    }
}
