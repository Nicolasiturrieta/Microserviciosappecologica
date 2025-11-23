package com.example.backend.dto;

import java.util.List;

public class RutaDTO {
    private String id;
    private String nombre;
    private String fecha;
    private String estado;
    private String choferId;
    private List<PuntoDTO> puntos;

    public RutaDTO(String id, String nombre, String fecha, String estado, String choferId, List<PuntoDTO> puntos) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.estado = estado;
        this.choferId = choferId;
        this.puntos = puntos;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public String getChoferId() {
        return choferId;
    }

    public List<PuntoDTO> getPuntos() {
        return puntos;
    }
}
