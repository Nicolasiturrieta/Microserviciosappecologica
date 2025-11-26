package com.example.backend.service;

import com.example.backend.domain.HistorialLabor;
import com.example.backend.dto.EmpleadoDTO;
import com.example.backend.dto.RankingEmpleadoDTO;
import com.example.backend.dto.RankingResponseDTO;
import com.example.backend.repository.EmpleadoRepository;
import com.example.backend.repository.HistorialLaborRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReporteService {

    private final EmpleadoRepository empleadoRepository;
    private final HistorialLaborRepository historialLaborRepository;

    public ReporteService(EmpleadoRepository empleadoRepository, HistorialLaborRepository historialLaborRepository) {
        this.empleadoRepository = empleadoRepository;
        this.historialLaborRepository = historialLaborRepository;
    }

    public RankingResponseDTO rankingEmpleados() {
        Map<Long, Long> cumplimientos = historialLaborRepository.findAll().stream()
                .collect(Collectors.groupingBy(h -> h.getEmpleado().getId(), Collectors.counting()));

        List<RankingEmpleadoDTO> ranking = empleadoRepository.findAll().stream()
                .map(emp -> {
                    float cumplimiento = cumplimientos.containsKey(emp.getId()) ? cumplimientos.get(emp.getId()) : 0;
                    // Normalizamos en 1.0 cuando no hay base de cálculo real
                    float score = cumplimiento > 0 ? Math.min(1f, cumplimiento / 10f) : 0.5f;
                    EmpleadoDTO empleadoDTO = MapperService.toEmpleadoDto(emp);
                    return new RankingEmpleadoDTO(empleadoDTO, score);
                })
                .collect(Collectors.toList());

        return new RankingResponseDTO(ranking);
    }
}
