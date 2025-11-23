package com.example.backend.repository;

import com.example.backend.domain.Ruta;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RutaRepository extends JpaRepository<Ruta, Long> {
    @EntityGraph(attributePaths = {"puntos", "chofer"})
    List<Ruta> findByChofer_Id(Long choferId);
}
