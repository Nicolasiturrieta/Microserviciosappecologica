package com.example.backend.service;

import com.example.backend.domain.Empleado;
import com.example.backend.dto.EmpleadoDTO;
import com.example.backend.dto.LoginRequestDTO;
import com.example.backend.dto.LoginResponseDTO;
import com.example.backend.repository.EmpleadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final EmpleadoRepository empleadoRepository;

    public AuthService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    public LoginResponseDTO login(LoginRequestDTO requestDTO) {
        logger.info("🔐 LOGIN ATTEMPT - RUT: {}", requestDTO.getRut());
        
        try {
            // Validaciones
            if (requestDTO.getRut() == null || requestDTO.getRut().isBlank()) {
                logger.warn("❌ Login rejected: empty RUT");
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "RUT es requerido");
            }
            if (requestDTO.getPassword() == null || requestDTO.getPassword().isBlank()) {
                logger.warn("❌ Login rejected for RUT {}: empty password", requestDTO.getRut());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password es requerido");
            }

            logger.debug("📊 Searching empleado with RUT: {}", requestDTO.getRut());
            
            // Buscar empleado
            Empleado empleado = empleadoRepository.findByRut(requestDTO.getRut())
                    .orElse(null);

            if (empleado == null) {
                logger.warn("❌ EMPLEADO NOT FOUND for RUT: {}", requestDTO.getRut());
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas");
            }

            logger.debug("✅ EMPLEADO FOUND: {} - {}", empleado.getRut(), empleado.getNombre());
            logger.debug("🔑 Verifying password...");

            // Verificar password SIN encoder (temporal)
            if (!empleado.getPassword().equals(requestDTO.getPassword())) {
                logger.warn("❌ PASSWORD MISMATCH for RUT: {}", requestDTO.getRut());
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales invalidas");
            }

            logger.info("🎉 LOGIN SUCCESSFUL for: {} ({})", empleado.getNombre(), empleado.getRut());
            
            // Convertir a DTO
            EmpleadoDTO empleadoDTO;
            try {
                empleadoDTO = MapperService.toEmpleadoDto(empleado);
                logger.debug("✅ EMPLEADO MAPPED TO DTO");
            } catch (Exception e) {
                logger.error("💥 ERROR mapping empleado to DTO: {}", e.getMessage());
                // Fallback: crear DTO manualmente
                empleadoDTO = createEmpleadoDTO(empleado);
            }

            return new LoginResponseDTO(empleadoDTO, "token-" + System.currentTimeMillis());

        } catch (ResponseStatusException e) {
            logger.warn("🚫 AUTH ERROR: {}", e.getReason());
            throw e;
        } catch (Exception e) {
            logger.error("💥 UNEXPECTED ERROR in login: {}", e.getMessage(), e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error interno del servidor");
        }
    }

    private EmpleadoDTO createEmpleadoDTO(Empleado empleado) {
        // Crear DTO manualmente si MapperService falla
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setId(empleado.getId());
        dto.setRut(empleado.getRut());
        dto.setNombre(empleado.getNombre());
        dto.setCorreo(empleado.getCorreo());
        dto.setTelefono(empleado.getTelefono());
        dto.setRol(empleado.getRol() != null ? empleado.getRol().name() : null);
        return dto;
    }
}
