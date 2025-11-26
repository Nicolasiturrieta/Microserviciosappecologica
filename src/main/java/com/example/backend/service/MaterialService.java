package com.example.backend.service;

import com.example.backend.domain.Material;
import com.example.backend.dto.MaterialDTO;
import com.example.backend.dto.MaterialRequest;
import com.example.backend.repository.MaterialRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialService(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    public MaterialDTO crear(MaterialRequest request) {
        Material material = new Material(request.getNombre(), request.getDescripcion());
        return MapperService.toDto(materialRepository.save(material));
    }

    public List<MaterialDTO> listar() {
        return materialRepository.findAll().stream()
                .map(MapperService::toDto)
                .collect(Collectors.toList());
    }

    public MaterialDTO actualizar(Long id, MaterialRequest request) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Material no encontrado"));
        material.setNombre(request.getNombre());
        material.setDescripcion(request.getDescripcion());
        return MapperService.toDto(materialRepository.save(material));
    }

    public void eliminar(Long id) {
        if (!materialRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Material no encontrado");
        }
        materialRepository.deleteById(id);
    }
}
