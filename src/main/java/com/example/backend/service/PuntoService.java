package com.example.backend.service;

import com.example.backend.domain.Cliente;
import com.example.backend.domain.PuntoRecoleccion;
import com.example.backend.dto.PuntoDTO;
import com.example.backend.dto.PuntoRequest;
import com.example.backend.repository.ClienteRepository;
import com.example.backend.repository.PuntoRecoleccionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PuntoService {

    private final PuntoRecoleccionRepository puntoRepository;
    private final ClienteRepository clienteRepository;

    public PuntoService(PuntoRecoleccionRepository puntoRepository, ClienteRepository clienteRepository) {
        this.puntoRepository = puntoRepository;
        this.clienteRepository = clienteRepository;
    }

    public List<PuntoDTO> listar() {
        return puntoRepository.findAll().stream()
                .map(MapperService::toDto)
                .collect(Collectors.toList());
    }

    public PuntoDTO crear(PuntoRequest request) {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        PuntoRecoleccion punto = new PuntoRecoleccion(
                cliente,
                request.getDireccion(),
                request.getMaterial(),
                request.getContenedor(),
                request.getObservaciones()
        );
        return MapperService.toDto(puntoRepository.save(punto));
    }

    public PuntoDTO actualizar(Long id, PuntoRequest request) {
        PuntoRecoleccion punto = puntoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Punto no encontrado"));
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        punto.setCliente(cliente);
        punto.setDireccion(request.getDireccion());
        punto.setMaterial(request.getMaterial());
        punto.setContenedor(request.getContenedor());
        punto.setObservaciones(request.getObservaciones());
        return MapperService.toDto(puntoRepository.save(punto));
    }

    public void eliminar(Long id) {
        if (!puntoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Punto no encontrado");
        }
        puntoRepository.deleteById(id);
    }

    public PuntoRecoleccion buscarPorId(Long id) {
        return puntoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Punto no encontrado"));
    }
}
