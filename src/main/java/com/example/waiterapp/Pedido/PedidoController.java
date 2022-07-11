package com.example.waiterapp.Pedido;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity<List<Pedido>> listaPedidos() {
        List<Pedido> pedidos = pedidoService.listaPedidos();
        return ResponseEntity.ok().body(pedidos);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> retornaPedidoById(@PathVariable Long idPedido){
        Pedido pedido = pedidoService.retornaPedidoById(idPedido);

        return ResponseEntity.ok().body(pedido);
    }

    @PostMapping
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

    @PutMapping("/{idPedido}")
    public ResponseEntity<Void> atualizaPedido(@Valid @RequestBody PedidoDTO pedidoDTO, @PathVariable Long idCardapio){
        Pedido pedido = pedidoService.transformarDTO(pedidoDTO);

        pedido.setId(idCardapio);
        pedidoService.atualizaPedido(pedido);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> deletePedido(@PathVariable Long idPedido){
        pedidoService.apagaPedido(idPedido);

        return ResponseEntity.noContent().build();
    }
}
