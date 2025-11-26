package com.example.backend.controller;

import com.example.backend.dto.FinalizarOrdenRequestDTO;
import com.example.backend.dto.OrdenRequest;
import com.example.backend.dto.OrdenServicioDTO;
import com.example.backend.service.OrdenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @GetMapping("/ordenes")
    public List<OrdenServicioDTO> listar() {
        return ordenService.listar();
    }

    @GetMapping("/ordenes/{rutaId}/{puntoId}")
    public OrdenServicioDTO obtener(@PathVariable Long rutaId, @PathVariable Long puntoId) {
        return ordenService.obtenerOrden(rutaId, puntoId);
    }

    @PostMapping("/ordenes")
    @ResponseStatus(HttpStatus.CREATED)
    public OrdenServicioDTO crear(@RequestBody @Valid OrdenRequest request) {
        return ordenService.crear(request);
    }

    @PutMapping("/ordenes/{id}")
    public OrdenServicioDTO actualizar(@PathVariable Long id, @RequestBody @Valid OrdenRequest request) {
        return ordenService.actualizar(id, request);
    }

    @PostMapping("/puntos/{puntoId}/iniciar")
    public OrdenServicioDTO iniciar(@PathVariable Long puntoId) {
        return ordenService.iniciarOrden(puntoId);
    }

    @PostMapping("/puntos/{puntoId}/finalizar")
    public OrdenServicioDTO finalizar(@PathVariable Long puntoId, @RequestBody(required = false) FinalizarOrdenRequestDTO request) {
        return ordenService.finalizarOrden(puntoId, request == null ? new FinalizarOrdenRequestDTO() : request);
    }

    @DeleteMapping("/ordenes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        ordenService.eliminar(id);
    }
}
