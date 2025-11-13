package com.fca.cafeteria.presentation;

import com.fca.cafeteria.config.DemoModeConfig;
import com.fca.cafeteria.data.Bebida;
import com.fca.cafeteria.domain.CafeteriaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/cafeteria")
public class CafeteriaController {

    @Autowired
    private CafeteriaDomain cafeteriaDomain;

    @Autowired
    private DemoModeConfig demoModeConfig;


    @PostMapping("/bebidas")
    public Bebida registrarBebida(@RequestBody Bebida bebida) {
      //Checar si es demo y mandar excepción
        if (demoModeConfig.isDemoMode()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "Operación no permitida en modo demo. Solo lectura."
            );
        }
        return cafeteriaDomain.registrarBebida(bebida, bebida.getTipoBebida());
    }

    @GetMapping("/bebidas")
    public List<Bebida> buscarBebida(@RequestParam String nombre) {
        return cafeteriaDomain.buscarPorNombre(nombre);
    }


}
