package com.tienda.crud_spring.Controllers;

import com.tienda.crud_spring.Models.Cliente;
import com.tienda.crud_spring.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
@CrossOrigin // Permite solicitudes desde diferentes orígenes
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Obtener lista de clientes
    @GetMapping("/listar")
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    // Guardar un nuevo cliente
    @PostMapping("/guardar")
    public ResponseEntity<Cliente> guardarCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.guardarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
    }

    // Actualizar un cliente existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteService.buscarClientePorId(id);

        if (clienteExistente.isPresent()) {
            cliente.setId(id);
            Cliente clienteActualizado = clienteService.actualizarCliente(cliente);
            return ResponseEntity.ok(clienteActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Retorna 404 si no se encuentra el cliente
        }
    }

    // Eliminar un cliente
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Long id) {
        if (clienteService.buscarClientePorId(id).isPresent()) {
            clienteService.eliminarCliente(id);
            return ResponseEntity.noContent().build(); // Retorna 204 sin contenido
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 si no se encuentra el cliente
        }
    }
}