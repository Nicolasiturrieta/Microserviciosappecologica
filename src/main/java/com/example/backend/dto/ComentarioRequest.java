package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class ComentarioRequest {

    @NotBlank
    private String comentario;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
