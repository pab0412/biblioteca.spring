package com.holamundo.ejemplo.holamundo.controller;

import com.holamundo.ejemplo.holamundo.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private List<Producto> listaProductos = new ArrayList<>(Arrays.asList(
            new Producto(1, "Laptop", 800000),
            new Producto(2, "Mouse", 15000),
            new Producto(3, "Teclado", 30000)
    ));

    // 🌐 GET básico de saludo
    @GetMapping("/saludo")
    public String saludoProducto() {
        return "Hola desde el controlador de productos";
    }

    // Listado de productos (modo texto)
    @GetMapping("/listar")
    public String listarProductos() {
        return "Aquí se listarán los productos (ejemplo)";
    }

    // Listado real de productos (JSON)
    @GetMapping("/listar2")
    public List<Producto> listarProductos2() {
        return listaProductos;
    }

    // Buscar producto por ID
    @GetMapping("/{idProducto}")
    public ResponseEntity<?> buscarProducto(@PathVariable int idProducto) {
        for (Producto producto : listaProductos) {
            if (producto.getId() == idProducto) {
                return ResponseEntity.ok(producto);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Producto con ID " + idProducto + " no encontrado.");
    }

    // Agregar producto (POST)
    @PostMapping
    public ResponseEntity<?> agregarProducto(@RequestBody Producto nuevoProducto) {
        listaProductos.add(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Producto agregado correctamente: " + nuevoProducto.getNombre());
    }

    // Actualizar producto (PUT)
    @PutMapping("/{idProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable int idProducto, @RequestBody Producto productoActualizado) {
        for (Producto producto : listaProductos) {
            if (producto.getId() == idProducto) {
                producto.setNombre(productoActualizado.getNombre());
                producto.setPrecio(productoActualizado.getPrecio());
                return ResponseEntity.ok("Producto actualizado correctamente");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    // Eliminar producto (DELETE)
    @DeleteMapping("/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int idProducto) {
        Iterator<Producto> iterator = listaProductos.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == idProducto) {
                iterator.remove();
                return ResponseEntity.ok("Producto eliminado correctamente");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
    }

    // Buscar por nombre (opcional)
    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorNombre(@RequestParam String nombre) {
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return ResponseEntity.ok(producto);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ese nombre");
    }
}
