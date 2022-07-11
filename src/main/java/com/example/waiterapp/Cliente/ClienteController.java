package com.example.waiterapp.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping({"/clientes"})
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listaClientes() {
        List<Cliente> clientes = clienteService.listaClientes();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> retornaClienteById(@PathVariable long idCliente){
        Cliente cliente = clienteService.retornaClienteById(idCliente);

        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<Void> insereCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.transformarDTO(clienteDTO);
        cliente = clienteService.insereCliente(cliente);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<Void> atualizaCliente(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long idCliente){
        Cliente cliente = clienteService.transformarDTO(clienteDTO);

        cliente.setId(idCliente);
        clienteService.atualizaCliente(cliente);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long idCliente){
        clienteService.apagaCliente(idCliente);

        return ResponseEntity.noContent().build();
    }
    
}
