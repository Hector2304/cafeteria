package com.fca.cafeteria.presentation;

import com.fca.cafeteria.config.DemoModeConfig;
import com.fca.cafeteria.data.Bebida;
import com.fca.cafeteria.domain.CafeteriaDomain;
import com.fca.cafeteria.dto.BebidaSalidaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.fca.cafeteria.dto.CrearBebidaDTO;


import java.util.List;


@RestController
@RequestMapping("/cafeteria")
public class CafeteriaController {

    @Autowired
    private CafeteriaDomain cafeteriaDomain;

    @Autowired
    private DemoModeConfig demoModeConfig;


    @PostMapping("/bebidas")
    public BebidaSalidaDTO registrar(@RequestBody CrearBebidaDTO dto) {  //DTO
      //Checar si es demo y mandar excepción
        if (demoModeConfig.isDemoMode()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Operación no permitida en modo demo. Solo lectura."
            );
        }
        return cafeteriaDomain.registrar(dto);
    }

    @GetMapping("/bebidas")
    public List<Bebida> buscarBebida(@RequestParam String nombre) {
        return cafeteriaDomain.buscarPorNombre(nombre);
    }


}
