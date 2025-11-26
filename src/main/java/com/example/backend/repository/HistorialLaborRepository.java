package com.example.backend.repository;

import com.example.backend.domain.HistorialLabor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialLaborRepository extends JpaRepository<HistorialLabor, Long> {
    List<HistorialLabor> findByEmpleado_Id(Long empleadoId);
}
