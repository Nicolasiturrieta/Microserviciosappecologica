package com.example.backend.dto;

import java.util.List;

public class RankingResponseDTO {
    private List<RankingEmpleadoDTO> ranking;

    public RankingResponseDTO(List<RankingEmpleadoDTO> ranking) {
        this.ranking = ranking;
    }

    public List<RankingEmpleadoDTO> getRanking() {
        return ranking;
    }
}
