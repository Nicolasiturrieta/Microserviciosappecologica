package com.example.backend.controller;

import com.example.backend.dto.ClienteDTO;
import com.example.backend.dto.AsignacionRequestDTO;
import com.example.backend.dto.CrearClienteRequestDTO;
import com.example.backend.dto.CrearClienteResponseDTO;
import com.example.backend.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteService.listar();
    }

    @GetMapping("/{id}")
    public ClienteDTO obtener(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CrearClienteResponseDTO crear(@RequestBody @Valid CrearClienteRequestDTO request) {
        return clienteService.crear(request);
    }

    @PutMapping("/{id}")
    public ClienteDTO actualizar(@PathVariable Long id, @RequestBody @Valid CrearClienteRequestDTO request) {
        return clienteService.actualizar(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
    }

    @PutMapping("/{id}/asignar")
    public ClienteDTO asignar(@PathVariable Long id, @RequestBody @Valid AsignacionRequestDTO request) {
        return clienteService.asignar(id, request.getIdChofer(), request.getIdVehiculo());
    }
}
