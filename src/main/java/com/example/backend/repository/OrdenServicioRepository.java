package com.example.backend.repository;

import com.example.backend.domain.OrdenServicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrdenServicioRepository extends JpaRepository<OrdenServicio, Long> {
    Optional<OrdenServicio> findByRuta_IdAndPunto_Id(Long rutaId, Long puntoId);
    Optional<OrdenServicio> findByPunto_Id(Long puntoId);
}
