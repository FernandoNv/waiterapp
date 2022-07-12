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

@RestController
@RequestMapping({"/pedidos"})
public class PedidoController {
    
    private PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService){
        this.pedidoService = pedidoService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Pedido>> listaPedidos() {
        List<Pedido> pedidos = pedidoService.listaPedidos();
        return ResponseEntity.ok().body(pedidos);
    }

    @GetMapping(value = "/{idPedido}", produces = "application/json")
    public ResponseEntity<Pedido> retornaPedidoById(@PathVariable Long idPedido){
        Pedido pedido = pedidoService.retornaPedidoById(idPedido);

        return ResponseEntity.ok().body(pedido);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Void> inserePedido(@Valid @RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoService.transformarDTO(pedidoDTO);
        pedido = pedidoService.inserePedido(pedido);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(pedido.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{idPedido}", consumes = "application/json")
    public ResponseEntity<Void> atualizaPedido(@Valid @RequestBody PedidoDTO pedidoDTO, @PathVariable Long idCardapio){
        Pedido pedido = pedidoService.transformarDTO(pedidoDTO);

        pedido.setId(idCardapio);
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
