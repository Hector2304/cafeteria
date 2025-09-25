package com.fca.cafeteria.presentation;

import com.fca.cafeteria.data.Bebida;
import com.fca.cafeteria.domain.CafeteriaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cafeteria")
public class CafeteriaController {

    @Autowired
    private CafeteriaDomain cafeteriaDomain;

    @PostMapping("/bebidas")
    public Bebida registrarBebida(@RequestBody Bebida bebida) {
        return cafeteriaDomain.registrarBebida(bebida, bebida.getTipoBebida());
    }

    @GetMapping("/bebidas")
    public List<Bebida> buscarBebida(@RequestParam String nombre) {
        return cafeteriaDomain.buscarPorNombre(nombre);
    }


}
