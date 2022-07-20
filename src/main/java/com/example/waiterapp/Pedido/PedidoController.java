package com.example.waiterapp.Pedido;

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
@RequestMapping(value = {"/pedidos"})
public class PedidoController {
    
    private PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

//    @GetMapping(produces = "application/json")
//    public ResponseEntity<List<Pedido>> listaPedidos() {
//        List<Pedido> pedidos = pedidoService.listaPedidos();
//        return ResponseEntity.ok().body(pedidos);
//    }

    @GetMapping(value = "clientes/{idCliente}", produces = "application/json")
    public ResponseEntity<List<Pedido>> retornaPedidoByIdCliente(@PathVariable Long idCliente){
        List<Pedido> pedidos = pedidoService.listaPedidosByIdCliente(idCliente);
        return ResponseEntity.ok().body(pedidos);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Pedido> inserePedido(@Valid @RequestBody Pedido pedido){
        System.out.print("Pedido enviado pelo request - ");
        System.out.println(pedido);
        pedido = pedidoService.inserePedido(pedido);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();

        return ResponseEntity.created(uri).body(pedido);
    }

    @PutMapping(value = "/{idPedido}", consumes = "application/json")
    public ResponseEntity<Void> atualizaPedido(@Valid @RequestBody Pedido pedido, @PathVariable Long idPedido){
        pedido.setId(idPedido);
        pedidoService.atualizaPedido(pedido);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{idPedido}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long idPedido){
        try{
            pedidoService.apagaPedido(idPedido);

            return ResponseEntity.noContent().build();
            
        }catch (DataIntegrityViolationException | ObjectNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
