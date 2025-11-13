package com.fca.cafeteria.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//Detecta e imprime en que modo se usa la aplicación
@Component
public class DemoModeConfig {

    @Value("${app.demo:false}")
    private boolean demoMode;

    public boolean isDemoMode() {
        return demoMode;
    }
    @PostConstruct
    public void init() {
        if (demoMode) {
            System.out.println(" Solo lectura habilitada.");
        } else {
            System.out.println(" Operaciones de escritura habilitadas.");
        }
    }

}
