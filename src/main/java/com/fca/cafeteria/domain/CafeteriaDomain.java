package com.fca.cafeteria.domain;

import com.fca.cafeteria.data.Bebida;
import com.fca.cafeteria.data.TipoBebida;
import com.fca.cafeteria.data.BebidaRepository;
import com.fca.cafeteria.data.TipoBebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


import java.util.Optional;  //Parote de chat para evitar muchas validaciones de null

@Service
public class CafeteriaDomain {

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private TipoBebidaRepository tipoBebidaRepository;

    public Bebida registrarBebida(Bebida bebida, TipoBebida tipoBebida) {
        // Validar si ya existe una bebida con el mismo nombre y tipo
        if (bebidaRepository.existsByNombreAndTipoBebida_Descripcion(
                bebida.getNombre(), tipoBebida.getDescripcion())) {
            return null; //
        }

        // Verificar si el tipo ya existe
        Optional<TipoBebida> tipoExistente = tipoBebidaRepository.findByDescripcion(tipoBebida.getDescripcion());

        if (tipoExistente.isPresent()) {
            bebida.setTipoBebida(tipoExistente.get());
        } else {
            TipoBebida nuevoTipo = tipoBebidaRepository.save(tipoBebida);
            bebida.setTipoBebida(nuevoTipo);
        }
        return bebidaRepository.save(bebida);
    }

    // Buscar por nombre pq si
    public List<Bebida> buscarPorNombre(String nombre) {
        return bebidaRepository.findByNombre(nombre);
    }


}
