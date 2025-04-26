package com.holamundo.ejemplo.holamundo.controller;

import com.holamundo.ejemplo.holamundo.model.SaludoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")


public class HolaMundoController {
    @GetMapping("/saludo")
    public String saludo() {
        return "Hola Mundo";
    }

    @GetMapping("/saludo2")
    public String saludo2(@RequestParam(defaultValue = "Erwin") String nombre) {
        return "Hola " + nombre;
    }

    @GetMapping("/saludojson")
    public SaludoResponse saludoJson(@RequestParam(defaultValue = "Erwin") String nombre) {
        String mensaje = "Hola " + nombre + ", ¡bienvenido a la API!";
        return new SaludoResponse(nombre, mensaje);
    }

    // Ruta y método para 'Hola a todos, mi nombre es Pedro'
    @GetMapping("/pedro")
    public String saludoPedro() {
        return "Hola a todos, mi nombre es Pedro";
    }

    // Operaciones matemáticas básicas

    // Ruta para la suma
    @GetMapping("/suma")
    public String suma(@RequestParam double a, @RequestParam double b) {
        double resultado = a + b;
        return "El resultado de la suma es: " + resultado;
    }

    // Ruta para la resta
    @GetMapping("/resta")
    public String resta(@RequestParam double a, @RequestParam double b) {
        double resultado = a - b;
        return "El resultado de la resta es: " + resultado;
    }

    // Ruta para la multiplicación
    @GetMapping("/multiplicacion")
    public String multiplicacion(@RequestParam double a, @RequestParam double b) {
        double resultado = a * b;
        return "El resultado de la multiplicación es: " + resultado;
    }

    // Ruta para la división
    @GetMapping("/division")
    public String division(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            return "Error: No se puede dividir por cero.";
        }
        double resultado = a / b;
        return "El resultado de la división es: " + resultado;
    }
}
