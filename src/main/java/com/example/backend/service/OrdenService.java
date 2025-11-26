package com.example.backend.service;

import com.example.backend.domain.OrdenEstado;
import com.example.backend.domain.OrdenServicio;
import com.example.backend.domain.PuntoRecoleccion;
import com.example.backend.domain.Ruta;
import com.example.backend.dto.FinalizarOrdenRequestDTO;
import com.example.backend.dto.OrdenRequest;
import com.example.backend.dto.OrdenServicioDTO;
import com.example.backend.repository.OrdenServicioRepository;
import com.example.backend.repository.RutaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdenService {

    private final OrdenServicioRepository ordenRepository;
    private final RutaRepository rutaRepository;
    private final PuntoService puntoService;

    public OrdenService(OrdenServicioRepository ordenRepository, RutaRepository rutaRepository, PuntoService puntoService) {
        this.ordenRepository = ordenRepository;
        this.rutaRepository = rutaRepository;
        this.puntoService = puntoService;
    }

    public OrdenServicioDTO obtenerOrden(Long rutaId, Long puntoId) {
        OrdenServicio orden = ordenRepository.findByRuta_IdAndPunto_Id(rutaId, puntoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada"));
        return MapperService.toDto(orden);
    }

    public OrdenServicioDTO iniciarOrden(Long puntoId) {
        OrdenServicio orden = ordenRepository.findByPunto_Id(puntoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada"));
        orden.setEstado(OrdenEstado.EN_PROCESO);
        return MapperService.toDto(ordenRepository.save(orden));
    }

    public OrdenServicioDTO finalizarOrden(Long puntoId, FinalizarOrdenRequestDTO request) {
        OrdenServicio orden = ordenRepository.findByPunto_Id(puntoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada"));
        orden.setEstado(OrdenEstado.COMPLETADA);
        orden.setObservacion(request.getObservacion());
        return MapperService.toDto(ordenRepository.save(orden));
    }

    public List<OrdenServicioDTO> listar() {
        return ordenRepository.findAll().stream()
                .map(MapperService::toDto)
                .collect(Collectors.toList());
    }

    public OrdenServicioDTO crear(OrdenRequest request) {
        Ruta ruta = rutaRepository.findById(request.getRutaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada"));
        PuntoRecoleccion punto = puntoService.buscarPorId(request.getPuntoId());

        OrdenServicio orden = new OrdenServicio(ruta, punto,
                request.getEstado() == null ? OrdenEstado.PENDIENTE : OrdenEstado.valueOf(request.getEstado()));
        if (request.getFotos() != null) {
            orden.setFotos(request.getFotos());
        }
        orden.setObservacion(request.getObservacion());
        return MapperService.toDto(ordenRepository.save(orden));
    }

    public OrdenServicioDTO actualizar(Long id, OrdenRequest request) {
        OrdenServicio orden = ordenRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada"));

        Ruta ruta = rutaRepository.findById(request.getRutaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ruta no encontrada"));
        PuntoRecoleccion punto = puntoService.buscarPorId(request.getPuntoId());

        orden.setRuta(ruta);
        orden.setPunto(punto);
        if (request.getEstado() != null) {
            orden.setEstado(OrdenEstado.valueOf(request.getEstado()));
        }
        if (request.getFotos() != null) {
            orden.setFotos(request.getFotos());
        }
        orden.setObservacion(request.getObservacion());
        return MapperService.toDto(ordenRepository.save(orden));
    }

    public void eliminar(Long id) {
        if (!ordenRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Orden no encontrada");
        }
        ordenRepository.deleteById(id);
    }
}
