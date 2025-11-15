package com.fca.cafeteria.domain;

import com.fca.cafeteria.data.Bebida;
import com.fca.cafeteria.data.TipoBebida;
import com.fca.cafeteria.data.BebidaRepository;
import com.fca.cafeteria.data.TipoBebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fca.cafeteria.dto.CrearBebidaDTO;
import com.fca.cafeteria.dto.BebidaSalidaDTO;
import java.util.List;


import java.util.Optional;  //Parote de chat para evitar muchas validaciones de null

@Service
public class CafeteriaDomain {

    @Autowired
    private BebidaRepository bebidaRepository;

    @Autowired
    private TipoBebidaRepository tipoBebidaRepository;

    public BebidaSalidaDTO registrar(CrearBebidaDTO dto) {

        // Validar duplicado de nombre y tipo
        if (bebidaRepository.existsByNombreAndTipoBebida_Descripcion(
                dto.getNombre(), dto.getTipo())) {
            return null;
        }

        // Buscar tipo
        Optional<TipoBebida> tipoExistente =
                tipoBebidaRepository.findByDescripcion(dto.getTipo());

        TipoBebida tipo;

        if (tipoExistente.isPresent()) {
            tipo = tipoExistente.get();
        } else {
            TipoBebida nuevoTipo = new TipoBebida();
            nuevoTipo.setDescripcion(dto.getTipo());
            tipo = tipoBebidaRepository.save(nuevoTipo);
        }

        Bebida bebida = new Bebida();
        bebida.setNombre(dto.getNombre());
        bebida.setDescripcion(dto.getDescripcion());
        bebida.setTipoBebida(tipo);

        Bebida guardada = bebidaRepository.save(bebida);

        // Preparar respuesta
        return new BebidaSalidaDTO(
                guardada.getId(),
                guardada.getNombre(),
                guardada.getDescripcion(),
                guardada.getTipoBebida().getDescripcion()
        );
    }


    // Buscar por nombre
    public List<Bebida> buscarPorNombre(String nombre) {
        return bebidaRepository.findByNombre(nombre);
    }


}
