package com.example.backend.service;

import com.example.backend.domain.Cliente;
import com.example.backend.domain.Empleado;
import com.example.backend.domain.HistorialLabor;
import com.example.backend.domain.Material;
import com.example.backend.domain.OrdenServicio;
import com.example.backend.domain.PuntoRecoleccion;
import com.example.backend.domain.Ruta;
import com.example.backend.domain.Vehiculo;
import com.example.backend.dto.ClienteDTO;
import com.example.backend.dto.CrearClienteResponseDTO;
import com.example.backend.dto.CrearEmpleadoResponseDTO;
import com.example.backend.dto.EmpleadoDTO;
import com.example.backend.dto.HistorialLaborDTO;
import com.example.backend.dto.MaterialDTO;
import com.example.backend.dto.OrdenServicioDTO;
import com.example.backend.dto.PuntoDTO;
import com.example.backend.dto.RutaDTO;
import com.example.backend.dto.VehiculoDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MapperService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public static EmpleadoDTO toEmpleadoDto(Empleado empleado) {
        return new EmpleadoDTO(
                empleado.getId(),
                empleado.getNombre(),
                empleado.getRut(),
                empleado.getCorreo(),
                empleado.getTelefono(),
                empleado.getRol().name(),
                null
        );
    }

    public static CrearEmpleadoResponseDTO toCrearEmpleadoResponseDto(Empleado empleado) {
        return new CrearEmpleadoResponseDTO(
                Math.toIntExact(empleado.getId()),
                empleado.getNombre(),
                empleado.getRut(),
                empleado.getCorreo(),
                empleado.getTelefono(),
                empleado.getRol().name(),
                null
        );
    }

    public static ClienteDTO toDto(Cliente cliente) {
        return new ClienteDTO(
                Math.toIntExact(cliente.getId()),
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

    public static CrearClienteResponseDTO toCrearClienteResponseDto(Cliente cliente) {
        return new CrearClienteResponseDTO(
                Math.toIntExact(cliente.getId()),
                cliente.getNombreEmpresa(),
                cliente.getRut(),
                cliente.getDireccion(),
                cliente.getTelefono(),
                cliente.getCorreo(),
                cliente.getMaterial(),
                cliente.getFrecuencia(),
                cliente.getFotoUri()
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

    public static VehiculoDTO toDto(Vehiculo vehiculo) {
        return new VehiculoDTO(
                vehiculo.getId(),
                vehiculo.getMarca(),
                vehiculo.getModelo(),
                vehiculo.getPatente(),
                vehiculo.getAnio(),
                vehiculo.getCapacidad()
        );
    }

    public static MaterialDTO toDto(Material material) {
        return new MaterialDTO(
                material.getId(),
                material.getNombre(),
                material.getDescripcion()
        );
    }

    public static HistorialLaborDTO toDto(HistorialLabor historial) {
        return new HistorialLaborDTO(
                historial.getRutaId().toString(),
                historial.getFecha(),
                historial.getPuntosCompletados(),
                historial.getObservacion(),
                historial.getEmpleado().getId().intValue()
        );
    }
}
