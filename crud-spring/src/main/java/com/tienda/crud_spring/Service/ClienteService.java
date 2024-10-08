package com.tienda.crud_spring.Service;

import com.tienda.crud_spring.Models.Cliente;
import com.tienda.crud_spring.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
  

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    // Listar todos los clientes
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // Guardar un nuevo cliente
    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);   
    }

    // Buscar cliente por ID
    public Optional<Cliente> buscarClientePorId(Long id) {
        return clienteRepository.findById(id);
    }

    // Actualizar cliente existente
    public Cliente actualizarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Eliminar cliente por ID
    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}