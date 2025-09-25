package com.fca.cafeteria.data;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface BebidaRepository extends JpaRepository<Bebida, Integer> {
    List<Bebida> findByNombre(String nombre);
    boolean existsByNombreAndTipoBebida_Descripcion(String nombre, String descripcion);

}
