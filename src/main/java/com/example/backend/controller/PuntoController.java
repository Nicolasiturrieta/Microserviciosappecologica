package com.example.backend.controller;

import com.example.backend.dto.PuntoDTO;
import com.example.backend.dto.PuntoRequest;
import com.example.backend.service.PuntoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/puntos")
public class PuntoController {

    private final PuntoService puntoService;

    public PuntoController(PuntoService puntoService) {
        this.puntoService = puntoService;
    }

    @GetMapping
    public List<PuntoDTO> listar() {
        return puntoService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PuntoDTO crear(@RequestBody @Valid PuntoRequest request) {
        return puntoService.crear(request);
    }

    @PutMapping("/{id}")
    public PuntoDTO actualizar(@PathVariable Long id, @RequestBody @Valid PuntoRequest request) {
        return puntoService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        puntoService.eliminar(id);
    }
}
