package com.holamundo.ejemplo.holamundo.controller;

import com.holamundo.ejemplo.holamundo.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    public List<Cliente> clientes = Arrays.asList(
            new Cliente(1, "Pablo", "pablo@example.com"),
            new Cliente(2, "Sebastian", "sebastian@example.com"),
            new Cliente(3,"Benjamin", "benjamin@example.com"),
            new Cliente(4, "Bastian", "bastian@example.com")
    );

    @GetMapping("/listar")
    public List<Cliente> listar() {
        return clientes;
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<?> buscar(@PathVariable int idCliente) {
        for (Cliente cliente : clientes){
            if (cliente.getId() == idCliente){
                return ResponseEntity.ok(cliente);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }

    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Cliente cliente) {
        clientes.add(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado correctamente " + cliente.getNombre());
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<?> actualizarCliente(@PathVariable Integer idCliente, @RequestBody Cliente cliente) {
        for (Cliente c : clientes) {
            if (c.getId() == idCliente) {
                c.setNombre(cliente.getNombre());
                c.setCorreo(cliente.getCorreo());
                return ResponseEntity.ok().body("Cliente actualizado correctamente " + c.getNombre());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<?> eliminarCliente(@PathVariable Integer idCliente) {
        Iterator<Cliente> it = clientes.iterator();
        while (it.hasNext()) {
            Cliente cliente = it.next();
            if (cliente.getId() == idCliente) {
                it.remove();
                return ResponseEntity.ok().body("Cliente eliminado correctamente");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarClientePorNombre(@RequestParam String nombre) {
        for (Cliente c : clientes) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return ResponseEntity.ok().body(c);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }
}
