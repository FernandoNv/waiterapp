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

import javax.transaction.Transactional;
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

        Cardapio cardapio1 = new Cardapio(null, LocalDateTime.now(), "Promoção do dia", "Os pratos mais pedidos com desconto");
        Cardapio cardapio2 = new Cardapio(null, LocalDateTime.now(), "Comida brasileira", "Coletanea com os pratos mais tipicos do brasil");

        //cardapioRepository.saveAll(Arrays.asList(cardapio1, cardapio2));

        Item bebida1 = new Bebida(null, "Coca Cola", null, LocalDateTime.now(), 15.0D, "2L");
        Item bebida2 = new Bebida(null, "Guarana Antartica", "Melhor refrigerante brasileiro", LocalDateTime.now(), 15.0D, "2,5L");
        Item prato1 = new Prato(
                null,
                "Frango com quiabo",
                "A clássica receita com todos aqueles detalhes que fazem a diferença. As coxas e sobrecoxas são braseadas, cozinham com pouco líquido, para que a carne fique úmida e a pele dourada.",
                LocalDateTime.now(),
                59.99D);
        Item prato2 = new Prato(
                null,
                "Bobó de camarão ",
                "O clássico baiano ganhou mais sabor com leite de coco caseiro e caldo de camarão, preparado com as cascas. Para ficar ainda mais arretado, sirva com farofa de coco, arroz branco e folhas de coentro.",
                LocalDateTime.now(),
                39.99D);

        cardapio1.getItems().addAll(Arrays.asList(bebida1, bebida2, prato1));
        cardapio2.getItems().addAll(Arrays.asList(bebida1, prato2));

        bebida1.getCardapios().addAll(Arrays.asList(cardapio1, cardapio2));
        bebida2.getCardapios().add(cardapio1);

        prato1.getCardapios().add(cardapio1);
        prato2.getCardapios().add(cardapio2);

        Ingrediente ingrediente1 = new Ingrediente(null, "quiabo (cerca de 20 unidades)", "", LocalDateTime.now(), 200.00f);
        Ingrediente ingrediente2 = new Ingrediente(null, "4 coxas de frango com pele e osso", "", LocalDateTime.now(), 500.00f);
        Ingrediente ingrediente3 = new Ingrediente(null, "cascas e cabeças camarão", "", LocalDateTime.now(), 1000.00f);

        ingrediente1.getPratos().add((Prato) prato1);
        ingrediente2.getPratos().add((Prato) prato1);
        ingrediente3.getPratos().add((Prato) prato2);

        ((Prato) prato1).getIngredientes().addAll(Arrays.asList(ingrediente1, ingrediente2));
        ((Prato) prato2).getIngredientes().add(ingrediente3);

        ingredienteRepository.saveAll(Arrays.asList(ingrediente1, ingrediente2, ingrediente3));
        itemRepository.saveAll(Arrays.asList(bebida1, bebida2, prato1, prato2));
        cardapioRepository.saveAll(Arrays.asList(cardapio1, cardapio2));

        Cliente cliente1 = new Cliente(null, "Fernando", null, "123.123.123-12", LocalDateTime.now());
        Cliente cliente2 = new Cliente(null, "Juliana", null, "000.000.000-01", LocalDateTime.now());

        Garcom garcom1 = new Garcom(null, "João", LocalDateTime.now(), null);
        Garcom garcom2 = new Garcom(null, "Pedro", LocalDateTime.now(), null);

        //public Pedido(Long id, LocalDateTime dataCriacao, Estado estado, Double precoTotal, Integer notaAtendimento, Integer notaPedido, String opcoesExtras) {
        Pedido pedido1 = new Pedido(null, LocalDateTime.now(), Estado.FECHADO, null, 10, 10, null);
        pedido1.setCliente(cliente1);
        pedido1.setGarcom(garcom1);

        Pedido pedido2 = new Pedido(null, LocalDateTime.now(), Estado.FECHADO, null, 5, 5, null);
        pedido2.setCliente(cliente2);
        pedido2.setGarcom(garcom2);

        garcom1.getPedidos().add(pedido1);
        cliente1.getPedidos().add(pedido1);

        garcom2.getPedidos().add(pedido2);
        cliente2.getPedidos().add(pedido2);

        Pagamento pagamento1 = new PagamentoComCartao(null, Estado.CONCLUIDO, LocalDateTime.now());
        pedido1.setPagamento(pagamento1);

        Pagamento pagamento2 = new PagamentoComCartao(null, Estado.CONCLUIDO, LocalDateTime.now());
        pedido2.setPagamento(pagamento2);

        clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
        garcomRepository.saveAll(Arrays.asList(garcom1, garcom2));
        pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
        pagamentoRepository.saveAll(Arrays.asList(pagamento1, pagamento2));

        //ItemPedido(Pedido pedido, Item item, Integer quantidade, Double preco)
        ItemPedido item1 = new ItemPedido(pedido1, bebida1, 2);
        ItemPedido item2 = new ItemPedido(pedido1, prato1, 2);

        ItemPedido item3 = new ItemPedido(pedido2, bebida2, 3);
        ItemPedido item4 = new ItemPedido(pedido2, prato1, 2);
        ItemPedido item5 = new ItemPedido(pedido2, prato2, 1);

        pedido1.getItems().addAll(Arrays.asList(item1, item2));
        pedido1.setPrecoTotal();
        pedido2.getItems().addAll(Arrays.asList(item3, item4, item5));
        pedido2.setPrecoTotal();

        bebida1.getItems().add(item1);
        prato1.getItems().add(item2);

        bebida2.getItems().add(item3);
        prato1.getItems().add(item4);
        prato2.getItems().add(item5);

        itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3, item4, item5));
        itemRepository.saveAll(Arrays.asList(bebida1, bebida2, prato1, prato2));
        pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
    }
}
