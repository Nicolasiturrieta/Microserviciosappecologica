package com.example.backend.dto;

import jakarta.validation.constraints.NotNull;

public class AsignacionRequestDTO {

    @NotNull
    private Long idChofer;

    @NotNull
    private Long idVehiculo;

    public Long getIdChofer() {
        return idChofer;
    }

    public void setIdChofer(Long idChofer) {
        this.idChofer = idChofer;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
}
