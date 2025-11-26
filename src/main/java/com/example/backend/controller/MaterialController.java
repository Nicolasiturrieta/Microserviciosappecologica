package com.example.backend.controller;

import com.example.backend.dto.MaterialDTO;
import com.example.backend.dto.MaterialRequest;
import com.example.backend.service.MaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiales")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialDTO crear(@RequestBody @Valid MaterialRequest request) {
        return materialService.crear(request);
    }

    @GetMapping
    public List<MaterialDTO> listar() {
        return materialService.listar();
    }

    @PutMapping("/{id}")
    public MaterialDTO actualizar(@PathVariable Long id, @RequestBody @Valid MaterialRequest request) {
        return materialService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        materialService.eliminar(id);
    }
}
