package com.fca.cafeteria.data;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TipoBebidaRepository extends JpaRepository<TipoBebida, Integer> {
    Optional<TipoBebida> findByDescripcion(String descripcion);
}
