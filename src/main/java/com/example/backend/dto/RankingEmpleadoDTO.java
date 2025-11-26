package com.example.backend.dto;

public class RankingEmpleadoDTO {
    private EmpleadoDTO empleado;
    private Float cumplimiento;

    public RankingEmpleadoDTO(EmpleadoDTO empleado, Float cumplimiento) {
        this.empleado = empleado;
        this.cumplimiento = cumplimiento;
    }

    public EmpleadoDTO getEmpleado() {
        return empleado;
    }

    public Float getCumplimiento() {
        return cumplimiento;
    }
}
