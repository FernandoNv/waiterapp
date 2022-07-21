package com.example.waiterapp.Cliente;

import com.example.waiterapp.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping({"/api/clientes"})
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService){
        this.clienteService = clienteService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Cliente>> listaClientes() {
        List<Cliente> clientes = clienteService.listaClientes();
        return ResponseEntity.ok().body(clientes);
    }

    @GetMapping(value = "/{idCliente}", produces = "application/json")
    public ResponseEntity<Cliente> retornaClienteById(@PathVariable long idCliente){
        Cliente cliente = clienteService.retornaClienteById(idCliente);

        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping(value = "/cpf",consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cliente> retornaClienteByCpf(@Valid @RequestBody ClienteDTO clienteDTO){
        Cliente cliente = clienteService.retornaClienteByCpf(clienteDTO.getCpf());

        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cliente> insereCliente(@Valid @RequestBody ClienteDTO clienteDTO){
        if(clienteDTO.getCpf() != null){
            Cliente cliente = clienteService.retornaClienteByCpf(clienteDTO.getCpf());
            if(cliente != null){
                return ResponseEntity.ok().body(cliente);
            }
        }
        Cliente cliente = clienteService.transformarDTO(clienteDTO);
        cliente = clienteService.insereCliente(cliente);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(cliente);
    }

    @PutMapping(value = "/{idCliente}", consumes = "application/json")
    public ResponseEntity<Void> atualizaCliente(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable Long idCliente){
        Cliente cliente = clienteService.transformarDTO(clienteDTO);

        cliente.setId(idCliente);
        clienteService.atualizaCliente(cliente);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{idCliente}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long idCliente){
        try{
            clienteService.apagaCliente(idCliente);
            return ResponseEntity.noContent().build();

        }catch (DataIntegrityViolationException | ObjectNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    
}
