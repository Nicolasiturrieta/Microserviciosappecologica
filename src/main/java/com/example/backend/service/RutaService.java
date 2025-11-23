package com.example.backend.service;

import com.example.backend.domain.Empleado;
import com.example.backend.domain.PuntoRecoleccion;
import com.example.backend.domain.Ruta;
import com.example.backend.dto.RutaDTO;
import com.example.backend.dto.RutaRequest;
import com.example.backend.repository.EmpleadoRepository;
import com.example.backend.repository.RutaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;
    private final EmpleadoRepository empleadoRepository;
    private final PuntoService puntoService;

    public RutaService(RutaRepository rutaRepository, EmpleadoRepository empleadoRepository, PuntoService puntoService) {
        this.rutaRepository = rutaRepository;
        this.empleadoRepository = empleadoRepository;
        this.puntoService = puntoService;
    }

    public List<RutaDTO> rutasParaChofer(Long choferId) {
        return rutaRepository.findByChofer_Id(choferId).stream()
                .map(MapperService::toDto)
                .collect(Collectors.toList());
    }

    public RutaDTO buscarPorId(Long id) {
        Ruta ruta = rutaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada"));
        return MapperService.toDto(ruta);
    }

    public RutaDTO crear(RutaRequest request) {
        Empleado chofer = empleadoRepository.findById(request.getChoferId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chofer no encontrado"));

        Ruta ruta = new Ruta(request.getNombre(), request.getFecha(), request.getEstado(), chofer);
        if (request.getPuntoIds() != null) {
            List<PuntoRecoleccion> puntos = request.getPuntoIds().stream()
                    .map(puntoService::buscarPorId)
                    .collect(Collectors.toCollection(ArrayList::new));
            ruta.setPuntos(puntos);
        }
        return MapperService.toDto(rutaRepository.save(ruta));
    }

    public RutaDTO actualizar(Long id, RutaRequest request) {
        Ruta ruta = rutaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada"));
        Empleado chofer = empleadoRepository.findById(request.getChoferId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chofer no encontrado"));

        ruta.setNombre(request.getNombre());
        ruta.setFecha(request.getFecha());
        ruta.setEstado(request.getEstado());
        ruta.setChofer(chofer);

        List<PuntoRecoleccion> puntos = request.getPuntoIds() == null ? new ArrayList<>() : request.getPuntoIds().stream()
                .map(puntoService::buscarPorId)
                .collect(Collectors.toCollection(ArrayList::new));
        ruta.setPuntos(puntos);

        return MapperService.toDto(rutaRepository.save(ruta));
    }

    public void eliminar(Long id) {
        if (!rutaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada");
        }
        rutaRepository.deleteById(id);
    }
}
