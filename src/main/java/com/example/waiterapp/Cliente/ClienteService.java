package com.example.waiterapp.Cliente;

import com.example.waiterapp.Pedido.Pedido;
import com.example.waiterapp.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente transformarDTO(ClienteDTO clienteDTO){
        Cliente cliente = new Cliente(
                clienteDTO.getId(),
                clienteDTO.getNome(),
                clienteDTO.getEmail(),
                clienteDTO.getCpf(),
                clienteDTO.getDataCriacao()
        );
        cliente.setPedidos(clienteDTO.getPedidos());
        return cliente;
    }

    public List<Cliente> listaClientes() {
        return clienteRepository.findAll();
    }

    public Cliente retornaClienteById(Long idCliente) {
        return clienteRepository.findById(idCliente).orElse(null);
    }

    public List<Pedido> retornaPedidosCliente(Long idCliente){
        return retornaClienteById(idCliente).getPedidos();
    }

    public void inserePedidosCliente(Long idCliente, List<Pedido> pedidos){
        Cliente cliente = retornaClienteById(idCliente);
        
        cliente.setPedidos(pedidos);
    }

    public Cliente insereCliente(Cliente cliente) {
        cliente.setId(null);
        cliente.setDataCriacao(LocalDateTime.now());
        return clienteRepository.save(cliente);
    }

    public Cliente atualizaCliente(Cliente cliente){
        retornaClienteById(cliente.getId());
        return clienteRepository.save(cliente);
    }
    
    public void apagaCliente(long idCliente) throws DataIntegrityViolationException{
        try{
            clienteRepository.deleteById(idCliente);
        }catch(DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Não é possível excluir esse cliente");
        }
    }

    public Cliente retornaClienteByCpf(String cpf) {
        return clienteRepository.findByCpf(cpf).orElse(null);
    }
}
