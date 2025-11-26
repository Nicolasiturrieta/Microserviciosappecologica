package com.example.backend.dto;

public class LoginResponseDTO {
    private EmpleadoDTO empleado;
    private String token;

    public LoginResponseDTO(EmpleadoDTO empleado, String token) {
        this.empleado = empleado;
        this.token = token;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public String getToken() {
        return token;
    }
}
