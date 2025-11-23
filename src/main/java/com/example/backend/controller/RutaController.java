package com.example.backend.controller;

import com.example.backend.dto.RutaDTO;
import com.example.backend.dto.RutaRequest;
import com.example.backend.service.RutaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RutaController {

    private final RutaService rutaService;

    public RutaController(RutaService rutaService) {
        this.rutaService = rutaService;
    }

    @GetMapping("/api/chofer/{choferId}/rutas")
    public List<RutaDTO> rutasParaChofer(@PathVariable Long choferId) {
        return rutaService.rutasParaChofer(choferId);
    }

    @GetMapping("/api/rutas/{rutaId}")
    public RutaDTO rutaPorId(@PathVariable Long rutaId) {
        return rutaService.buscarPorId(rutaId);
    }

    @PostMapping("/api/rutas")
    @ResponseStatus(HttpStatus.CREATED)
    public RutaDTO crear(@RequestBody @Valid RutaRequest request) {
        return rutaService.crear(request);
    }

    @PutMapping("/api/rutas/{id}")
    public RutaDTO actualizar(@PathVariable Long id, @RequestBody @Valid RutaRequest request) {
        return rutaService.actualizar(id, request);
    }

    @DeleteMapping("/api/rutas/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        rutaService.eliminar(id);
    }
}
