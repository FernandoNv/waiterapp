package com.example.waiterapp.Pedido;

import com.example.waiterapp.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {
    
    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository){
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido transformarDTO(PedidoDTO pedidoDTO){
        Pedido pedido = new Pedido(pedidoDTO.getId(), pedidoDTO.getDataCriacao(), pedidoDTO.getEstado(), pedidoDTO.getPrecoTotal(), pedidoDTO.getNotaAtendimento(), pedidoDTO.getNotaPedido(), pedidoDTO.getOpcoesExtras());
        pedido.setItems(pedidoDTO.getItems());
        return pedido;
    }

    public List<Pedido> listaPedidos(){
        return pedidoRepository.findAll();
    }

    public Pedido retornaPedidoById(Long idPedido){
        return pedidoRepository.findById(idPedido).orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! ID: " + idPedido + ", Tipo: " + Pedido.class.getName()));
    }

    public Pedido inserePedido(Pedido pedido){
        pedido.setId(null);
        pedido.setDataCriacao(LocalDateTime.now());
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizaPedido(Pedido pedido){
        retornaPedidoById(pedido.getId());
        return pedidoRepository.save(pedido);
    }

    public void apagaPedido(Long idPedido) throws DataIntegrityViolationException{
        retornaPedidoById(idPedido);
        try{
            pedidoRepository.deleteById(idPedido);
        }catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(("Não é possível excluir esse pedido"));
        }
    }
}
