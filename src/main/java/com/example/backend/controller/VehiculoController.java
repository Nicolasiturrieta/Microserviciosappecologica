package com.example.backend.controller;

import com.example.backend.dto.VehiculoDTO;
import com.example.backend.dto.VehiculoRequest;
import com.example.backend.service.VehiculoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private final VehiculoService vehiculoService;

    public VehiculoController(VehiculoService vehiculoService) {
        this.vehiculoService = vehiculoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VehiculoDTO crear(@RequestBody @Valid VehiculoRequest request) {
        return vehiculoService.crear(request);
    }

    @GetMapping
    public List<VehiculoDTO> listar() {
        return vehiculoService.listar();
    }

    @PutMapping("/{id}")
    public VehiculoDTO actualizar(@PathVariable Long id, @RequestBody @Valid VehiculoRequest request) {
        return vehiculoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        vehiculoService.eliminar(id);
    }
}
