package com.example.backend.service;

import com.example.backend.domain.*;
import com.example.backend.dto.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MapperService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static UsuarioDTO toDto(Empleado empleado) {
        return new UsuarioDTO(
                empleado.getId().toString(),
                empleado.getNombre(),
                empleado.getRut(),
                empleado.getTelefono(),
                empleado.getCorreo(),
                empleado.getRol().name()
        );
    }

    public static ClienteDTO toDto(Cliente cliente) {
        return new ClienteDTO(
                cliente.getId().toString(),
                cliente.getNombreEmpresa(),
                cliente.getRut(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getCorreo(),
                cliente.getMaterial(),
                cliente.getFrecuencia(),
                cliente.getFotoUri(),
                cliente.getIdChoferAsignado(),
                cliente.getIdVehiculoAsignado()
        );
    }

    public static PuntoDTO toDto(PuntoRecoleccion punto) {
        return new PuntoDTO(
                punto.getId().toString(),
                punto.getCliente().getId().toString(),
                punto.getDireccion(),
                punto.getMaterial(),
                punto.getContenedor(),
                punto.getObservaciones()
        );
    }

    public static RutaDTO toDto(Ruta ruta) {
        List<PuntoDTO> puntos = ruta.getPuntos().stream()
                .map(MapperService::toDto)
                .collect(Collectors.toList());

        return new RutaDTO(
                ruta.getId().toString(),
                ruta.getNombre(),
                ruta.getFecha().format(DATE_FORMATTER),
                ruta.getEstado(),
                ruta.getChofer().getId().toString(),
                puntos
        );
    }

    public static OrdenServicioDTO toDto(OrdenServicio orden) {
        return new OrdenServicioDTO(
                orden.getId().toString(),
                orden.getRuta().getId().toString(),
                orden.getPunto().getId().toString(),
                orden.getEstado().name(),
                orden.getFotos(),
                orden.getObservacion()
        );
    }
}
