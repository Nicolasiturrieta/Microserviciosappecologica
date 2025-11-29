package com.example.backend.dto;

public class LoginResponseDTO {
    private EmpleadoDTO empleado;
    private String token;
    private String message;
    private boolean success;
    private int statusCode;

    // Constructor para éxito
    public LoginResponseDTO(EmpleadoDTO empleado, String token) {
        this.empleado = empleado;
        this.token = token;
        this.success = true;
        this.message = "Login exitoso";
        this.statusCode = 200;
    }

    // Constructor para errores
    public LoginResponseDTO(EmpleadoDTO empleado, String message, boolean success, int statusCode) {
        this.empleado = empleado;
        this.message = message;
        this.success = success;
        this.statusCode = statusCode;
        this.token = "";
    }

    // Getters y Setters
    public EmpleadoDTO getEmpleado() { return empleado; }
    public void setEmpleado(EmpleadoDTO empleado) { this.empleado = empleado; }
    
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    
    public int getStatusCode() { return statusCode; }
    public void setStatusCode(int statusCode) { this.statusCode = statusCode; }
}