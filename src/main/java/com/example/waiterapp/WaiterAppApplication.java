package com.example.waiterapp;

import com.example.waiterapp.Cardapio.Cardapio;
import com.example.waiterapp.Cardapio.CardapioRepository;
import com.example.waiterapp.Cliente.Cliente;
import com.example.waiterapp.Cliente.ClienteRepository;
import com.example.waiterapp.Garcom.Garcom;
import com.example.waiterapp.Garcom.GarcomRepository;
import com.example.waiterapp.Ingrediente.Ingrediente;
import com.example.waiterapp.Ingrediente.IngredienteRepository;
import com.example.waiterapp.Item.Bebida.Bebida;
import com.example.waiterapp.Item.Item;
import com.example.waiterapp.Item.ItemRepository;
import com.example.waiterapp.Item.Prato.Prato;
import com.example.waiterapp.ItemPedido.ItemPedido;
import com.example.waiterapp.ItemPedido.ItemPedidoRepository;
import com.example.waiterapp.Pagamento.PagamentoComCartao.PagamentoComCartao;
import com.example.waiterapp.Pagamento.Pagamento;
import com.example.waiterapp.Pagamento.PagamentoRepository;
import com.example.waiterapp.Pedido.Pedido;
import com.example.waiterapp.Pedido.PedidoRepository;
import com.example.waiterapp.enums.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
@RestController
public class WaiterAppApplication implements CommandLineRunner {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private GarcomRepository garcomRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public static void main(String[] args) {
        SpringApplication.run(WaiterAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Cardapio cardapio1 = new Cardapio(null, LocalDateTime.now(), "Cardapio 1", "Descricao do cardapio1");
        Cardapio cardapio2 = new Cardapio(null, LocalDateTime.now(), "Cardapio 2", "Descricao do cardapio2");

        //cardapioRepository.saveAll(Arrays.asList(cardapio1, cardapio2));

        Item bebida1 = new Bebida(null, "Bebida 1", null, LocalDateTime.now(), 3.50D, "300mL");
        Item bebida2 = new Bebida(null, "Bebida 2", "Descricao Bebida 2", LocalDateTime.now(), 19.99D, "1L");
        Item prato1 = new Prato(null, "Prato 1", "Descricao Prato 1", LocalDateTime.now(), 9.99D);
        Item prato2 = new Prato(null, "Prato 2", "Descricao Prato 2", LocalDateTime.now(), 199.99D);

        cardapio1.getItems().addAll(Arrays.asList(bebida1, bebida2, prato1));
        cardapio2.getItems().addAll(Arrays.asList(bebida1, prato2));

        bebida1.getCardapios().addAll(Arrays.asList(cardapio1, cardapio2));
        bebida2.getCardapios().add(cardapio1);

        prato1.getCardapios().add(cardapio1);
        prato2.getCardapios().add(cardapio2);

        Ingrediente ingrediente1 = new Ingrediente(null, "Ingrediente 1", "", LocalDateTime.now(), 300.00f);
        Ingrediente ingrediente2 = new Ingrediente(null, "Ingrediente 2", "", LocalDateTime.now(), 100.00f);
        Ingrediente ingrediente3 = new Ingrediente(null, "Ingrediente 3", "", LocalDateTime.now(), 250.00f);

        ingrediente1.getPratos().add((Prato) prato1);
        ingrediente2.getPratos().add((Prato) prato1);
        ingrediente3.getPratos().add((Prato) prato2);

        ((Prato) prato1).getIngredientes().addAll(Arrays.asList(ingrediente1, ingrediente2));
        ((Prato) prato2).getIngredientes().add(ingrediente3);

        ingredienteRepository.saveAll(Arrays.asList(ingrediente1, ingrediente2, ingrediente3));
        itemRepository.saveAll(Arrays.asList(bebida1, bebida2, prato1, prato2));
        cardapioRepository.saveAll(Arrays.asList(cardapio1, cardapio2));

        Cliente cliente1 = new Cliente(null, "Fernando", null, null, LocalDateTime.now());
        Cliente cliente2 = new Cliente(null, "Juliana", null, null, LocalDateTime.now());

        Garcom garcom1 = new Garcom(null, "Garcom 1", LocalDateTime.now(), null);
        Garcom garcom2 = new Garcom(null, "Garcom 2", LocalDateTime.now(), null);

        //public Pedido(Long id, LocalDateTime dataCriacao, Estado estado, Double precoTotal, Integer notaAtendimento, Integer notaPedido, String opcoesExtras) {
        Pedido pedido1 = new Pedido(null, LocalDateTime.now(), Estado.EMPREPARACAO, null, 10, 10, null);
        pedido1.setCliente(cliente1);
        pedido1.setGarcom(garcom1);

        garcom1.getPedidos().add(pedido1);
        cliente1.getPedidos().add(pedido1);

        Pagamento pagamento1 = new PagamentoComCartao(null, Estado.CONCLUIDO, LocalDateTime.now());
        pedido1.setPagamento(pagamento1);

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
        garcomRepository.saveAll(Arrays.asList(garcom1, garcom2));
        pedidoRepository.save(pedido1);
        pagamentoRepository.save(pagamento1);

        //ItemPedido(Pedido pedido, Item item, Integer quantidade, Double preco)
        ItemPedido item1 = new ItemPedido(pedido1, bebida1, 2);
        ItemPedido item2 = new ItemPedido(pedido1, prato1, 2);

        pedido1.getItems().addAll(Arrays.asList(item1, item2));
        pedido1.setPrecoTotal();
        bebida1.getItems().add(item1);
        prato1.getItems().add(item2);

        itemPedidoRepository.saveAll(Arrays.asList(item1, item2));
        itemRepository.saveAll(Arrays.asList(bebida1, bebida2, prato1, prato2));
        pedidoRepository.save(pedido1);
    }
}
