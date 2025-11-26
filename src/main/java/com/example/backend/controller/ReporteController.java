package com.example.backend.controller;

import com.example.backend.dto.RankingResponseDTO;
import com.example.backend.service.ReporteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/ranking-empleados")
    public RankingResponseDTO rankingEmpleados() {
        return reporteService.rankingEmpleados();
    }
}
