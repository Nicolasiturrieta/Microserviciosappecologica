package com.example.backend.service;

import com.example.backend.domain.Cliente;
import com.example.backend.dto.ClienteDTO;
import com.example.backend.dto.CrearClienteRequestDTO;
import com.example.backend.dto.CrearClienteResponseDTO;
import com.example.backend.repository.ClienteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> listar() {
        return clienteRepository.findAll().stream()
                .map(MapperService::toDto)
                .collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(MapperService::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
    }

    public CrearClienteResponseDTO crear(CrearClienteRequestDTO request) {
        Cliente cliente = new Cliente(
                request.getNombreEmpresa(),
                request.getRut(),
                request.getDireccion(),
                request.getTelefono(),
                request.getCorreo(),
                request.getMaterial(),
                request.getFrecuencia(),
                request.getFotoUri(),
                null,
                null
        );
        return MapperService.toCrearClienteResponseDto(clienteRepository.save(cliente));
    }

    public ClienteDTO actualizar(Long id, CrearClienteRequestDTO request) {
        Cliente existente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));

        existente.setNombreEmpresa(request.getNombreEmpresa());
        existente.setRut(request.getRut());
        existente.setDireccion(request.getDireccion());
        existente.setTelefono(request.getTelefono());
        existente.setCorreo(request.getCorreo());
        existente.setMaterial(request.getMaterial());
        existente.setFrecuencia(request.getFrecuencia());
        existente.setFotoUri(request.getFotoUri());

        return MapperService.toDto(clienteRepository.save(existente));
    }

    public void eliminar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado");
        }
        clienteRepository.deleteById(id);
    }

    public ClienteDTO asignar(Long id, Long idChofer, Long idVehiculo) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente no encontrado"));
        cliente.setIdChoferAsignado(idChofer);
        cliente.setIdVehiculoAsignado(idVehiculo);
        return MapperService.toDto(clienteRepository.save(cliente));
    }
}
