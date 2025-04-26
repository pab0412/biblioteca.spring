package com.holamundo.ejemplo.holamundo.controller;
import com.holamundo.ejemplo.holamundo.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Arrays;
@RestController

@RequestMapping("/api/productos")
public class ProductoController {
    private int idProducto = 1;
    private List<Producto> listarProductos = Arrays.asList(
            new Producto(idProducto++, "Notebook Lenovo", 550000),
            new Producto(idProducto++, "Mouse Logitech", 15000),
            new Producto(idProducto++, "Monitor Samsung", 120000)
    );

    @GetMapping("/saludo")
    public String saludoProducto(){
        return "Hola desde el controlador de Productos";
    }

    @GetMapping("/listar")
    public List<Producto> listarProductos(){
        return listarProductos;
    }

    @GetMapping("/{idProducto}") // Mismo nombre de la varibale de la carpeta modelo
    public ResponseEntity<Producto> buscarProductoPorID(@PathVariable int idProducto){
        for (Producto producto : listarProductos) {
            if (producto.getId() == idProducto) {
                return new ResponseEntity<>(producto, HttpStatus.OK);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<?> insertarProducto(@RequestBody Producto producto){
        listarProductos.add(producto);
        return ResponseEntity.status(HttpStatus.OK).body("Producto creado correctamente " + producto.getNombre());
    }
}
