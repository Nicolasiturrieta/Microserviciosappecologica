package com.example.backend.dto;

import jakarta.validation.constraints.NotBlank;

public class ComentarioRequestDTO {

    @NotBlank
    private String comentario;

    public String getComentario() {
        return comentario;
    }
}
