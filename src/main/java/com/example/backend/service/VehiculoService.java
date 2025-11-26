package com.example.backend.service;

import com.example.backend.domain.Vehiculo;
import com.example.backend.dto.CrearVehiculoRequestDTO;
import com.example.backend.dto.VehiculoDTO;
import com.example.backend.repository.VehiculoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }

    public VehiculoDTO crear(CrearVehiculoRequestDTO request) {
        Vehiculo vehiculo = new Vehiculo(
                request.getMarca(),
                request.getModelo(),
                request.getPatente(),
                request.getAnio(),
                request.getCapacidad()
        );
        return MapperService.toDto(vehiculoRepository.save(vehiculo));
    }

    public List<VehiculoDTO> listar() {
        return vehiculoRepository.findAll().stream()
                .map(MapperService::toDto)
                .collect(Collectors.toList());
    }

    public VehiculoDTO actualizar(Long id, CrearVehiculoRequestDTO request) {
        Vehiculo vehiculo = vehiculoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado"));
        vehiculo.setMarca(request.getMarca());
        vehiculo.setModelo(request.getModelo());
        vehiculo.setPatente(request.getPatente());
        vehiculo.setAnio(request.getAnio());
        vehiculo.setCapacidad(request.getCapacidad());
        return MapperService.toDto(vehiculoRepository.save(vehiculo));
    }

    public void eliminar(Long id) {
        if (!vehiculoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado");
        }
        vehiculoRepository.deleteById(id);
    }
}
