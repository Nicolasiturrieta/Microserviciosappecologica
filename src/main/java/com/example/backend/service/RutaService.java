package com.example.backend.service;

import com.example.backend.domain.Empleado;
import com.example.backend.domain.Ruta;
import com.example.backend.dto.CrearRutaRequestDTO;
import com.example.backend.dto.RutaDTO;
import com.example.backend.repository.EmpleadoRepository;
import com.example.backend.repository.RutaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RutaService {

    private final RutaRepository rutaRepository;
    private final EmpleadoRepository empleadoRepository;

    public RutaService(RutaRepository rutaRepository, EmpleadoRepository empleadoRepository) {
        this.rutaRepository = rutaRepository;
        this.empleadoRepository = empleadoRepository;
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

    public RutaDTO crear(CrearRutaRequestDTO request) {
        Long choferId = Long.parseLong(request.getIdChoferAsignado());
        Empleado chofer = empleadoRepository.findById(choferId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chofer no encontrado"));

        LocalDate fecha = LocalDate.parse(request.getFecha(), DateTimeFormatter.ISO_LOCAL_DATE);
        Ruta ruta = new Ruta(request.getNombre(), fecha, request.getEstado(), chofer);

        return MapperService.toDto(rutaRepository.save(ruta));
    }

    public RutaDTO actualizar(Long id, CrearRutaRequestDTO request) {
        Ruta ruta = rutaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada"));
        Long choferId = Long.parseLong(request.getIdChoferAsignado());
        Empleado chofer = empleadoRepository.findById(choferId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chofer no encontrado"));

        LocalDate fecha = LocalDate.parse(request.getFecha(), DateTimeFormatter.ISO_LOCAL_DATE);
        ruta.setNombre(request.getNombre());
        ruta.setFecha(fecha);
        ruta.setEstado(request.getEstado());
        ruta.setChofer(chofer);

        return MapperService.toDto(rutaRepository.save(ruta));
    }

    public void eliminar(Long id) {
        if (!rutaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada");
        }
        rutaRepository.deleteById(id);
    }
}
