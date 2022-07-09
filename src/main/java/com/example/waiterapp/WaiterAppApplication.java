package com.example.waiterapp;

import com.example.waiterapp.Cardapio.Cardapio;
import com.example.waiterapp.Cardapio.CardapioRepository;
import com.example.waiterapp.Cliente.Cliente;
import com.example.waiterapp.Cliente.ClienteRepository;
import com.example.waiterapp.Item.Bebida.Bebida;
import com.example.waiterapp.Item.Item;
import com.example.waiterapp.Item.ItemRepository;
import com.example.waiterapp.Item.Prato.Prato;
import com.example.waiterapp.ItemPedido.ItemPedido;
import com.example.waiterapp.ItemPedido.ItemPedidoRepository;
import com.example.waiterapp.Pagamento.PagamentoComCartao.PagamentoComCartao;
import com.example.waiterapp.Pagamento.PagamentoComDinheiro.PagamentoComDinheiro;
import com.example.waiterapp.Pagamento.Pagamento;
import com.example.waiterapp.Pagamento.PagamentoRepository;
import com.example.waiterapp.Pedido.Pedido;
import com.example.waiterapp.Pedido.PedidoRepository;
import com.example.waiterapp.enums.Estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootApplication
@RestController
public class WaiterAppApplication implements CommandLineRunner {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PedidoRepository PedidoRepository;

    @Autowired
    private ItemPedidoRepository ItemPedidoRepository;

    @Autowired
    private ClienteRepository ClienteRepository;

    @Autowired
    private PagamentoRepository PagamentoRepository;

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

        //itemRepository.saveAll(Arrays.asList(bebida1, bebida2));

//        cardapio1 = cardapioRepository.findById(1L).orElseThrow();
//        cardapio2 = cardapioRepository.findById(2L).orElseThrow();
//        bebida1 = itemRepository.findById(1L).orElseThrow();
//        bebida2 = itemRepository.findById(2L).orElseThrow();

        System.out.println(cardapio1);
        System.out.println(cardapio2);
        System.out.println(bebida1);
        System.out.println(bebida2);

        cardapio1.getItems().addAll(Arrays.asList(bebida1, bebida2, prato1));
        cardapio2.getItems().addAll(Arrays.asList(bebida1, prato2));

        bebida1.getCardapios().addAll(Arrays.asList(cardapio1, cardapio2));
        bebida2.getCardapios().add(cardapio1);

        prato1.getCardapios().add(cardapio1);
        prato2.getCardapios().add(cardapio2);

        itemRepository.saveAll(Arrays.asList(bebida1, bebida2, prato1, prato2));
        cardapioRepository.saveAll(Arrays.asList(cardapio1, cardapio2));

    }

    @GetMapping("/create_pedido")
    public long create_pedido() {
        Pedido pedido;

        if (PedidoRepository.count() == 0){
            pedido = new Pedido((long) 1, LocalDateTime.now(), Estado.PENDENTE, null, null, null, null);
        }else{
            List<Pedido> pedidos = PedidoRepository.findAll();
            long max_id = 0;
            for (int i = 0; i < pedidos.size(); i++){
                long id = pedidos.get(i).getId();

                if (id > max_id){
                    max_id = pedidos.get(i).getId();
                }
            }
            pedido = new Pedido(max_id+1, LocalDateTime.now(), Estado.PENDENTE, null, null, null, null);
        }
        PedidoRepository.save(pedido);

        return pedido.getId();
    }

    @GetMapping("/get_cardapios")
    public List<Cardapio> get_cardapios() {
        return cardapioRepository.findAll();
    }

    @GetMapping("/get_itens_cardapio")
    public List<Item> get_cardapio(@RequestParam() long id_cardapio) {
        Cardapio cardapio = null;

        try{
            cardapio = cardapioRepository.findById(id_cardapio).orElse(null);
            
            System.out.println(cardapio);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }
   
        return cardapio.getItems();
    }

    @GetMapping("/get_pedidos")
    public List<Pedido> get_pedidos() {

        return PedidoRepository.findAll();
    }

    @GetMapping("/get_pedido")
    public Pedido get_pedido(@RequestParam() long id_pedido) {
        Pedido pedido = null;

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }

        return pedido;
    }

    @GetMapping("/set_item_pedido")
    public Pedido set_item_pedido(@RequestParam() Item item, int quantidade, double preco) {
        Pedido pedido;

        if (PedidoRepository.count() == 0){
            pedido = new Pedido((long) 1, LocalDateTime.now(), Estado.PENDENTE, null, null, null, null);
        }else{
            List<Pedido> pedidos = PedidoRepository.findAll();
            long max_id = 0;
            for (int i = 0; i < pedidos.size(); i++){
                long id = pedidos.get(i).getId();

                if (id > max_id){
                    max_id = pedidos.get(i).getId();
                }
            }
            pedido = new Pedido(max_id+1, LocalDateTime.now(), Estado.PENDENTE, null, null, null, null);
        }
        PedidoRepository.save(pedido);

        ItemPedido item_pedido = new ItemPedido(pedido, item, quantidade, preco);

        ItemPedidoRepository.save(item_pedido);

        return pedido;
    }

    @GetMapping("/set_item_extra")
    public String set_item_extra(@RequestParam() long id_pedido, long id_item_extra) {
        Pedido pedido;
        Item item;

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);
            item = itemRepository.findById(id_item_extra).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }

        pedido.adicionarItemExtra(item);

        return "Item extra adicionado";
    }

    @GetMapping("/set_opcao_extra")
    public String set_opcao_extra(@RequestParam() long id_pedido, String opcoes_extra) {
        Pedido pedido;

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }

        pedido.setOpcoesExtras(opcoes_extra);

        return "Opção extra adicionada";
    }

    @GetMapping("/get_cliente_pedido")
    public Cliente get_cliente_pedido(@RequestParam() long id_pedido) {
        Pedido pedido;

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }

        return pedido.getCliente();
    }

    @GetMapping("/set_cliente_pedido")
    public String set_cliente_pedido(@RequestParam() long id_pedido, String nome, String email, String cpf) {
        Pedido pedido;
        Cliente cliente;

        if (ClienteRepository.count() == 0){
            cliente = new Cliente((long) 1, nome, email, cpf, LocalDateTime.now());
        }else{
            List<Cliente> clientes = ClienteRepository.findAll();
            long max_id = 0;
            for (int i = 0; i < clientes.size(); i++){
                long id = clientes.get(i).getId();

                if (id > max_id){
                    max_id = clientes.get(i).getId();
                }
            }
            cliente = new Cliente(max_id, nome, email, cpf, LocalDateTime.now());
        }

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }
        pedido.setCliente(cliente);

        return "Cliente definido";
    }

    @GetMapping("/get_nota_pedido")
    public String get_nota_pedido(@RequestParam() long id_pedido) {
        Pedido pedido;

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }

        return String.valueOf(pedido.getNotaPedido());
    }

    @GetMapping("/set_nota_pedido")
    public String set_nota_pedido(@RequestParam() long id_pedido, int nota) {
        Pedido pedido;

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }
        pedido.setNotaPedido(nota);

        return "Pedido avaliado";
    }

    @GetMapping("/get_nota_atendimento")
    public String get_nota_atendimento(@RequestParam() long id_pedido) {
        Pedido pedido;

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }

        return String.valueOf(pedido.getNotaAtendimento());
    }

    @GetMapping("/set_nota_atendimento")
    public String set_nota_atendimento(@RequestParam() long id_pedido, int nota) {
        Pedido pedido;

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }
        pedido.setNotaAtendimento(nota);

        return "Atendimento avaliado";
    }

    @GetMapping("/chamar_garcom")
    public String chamar_garcom(@RequestParam() long id_pedido) {
        Pedido pedido = null;

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }

        pedido.chamarGarcom();

        return "Garçom solicitado";
    }

    @GetMapping("/set_pagamento_cartao")
    public String set_pagamento_cartao(@RequestParam() long id_pedido, long id_pagamento, String estadoPagamento) {
        Pedido pedido;
        PagamentoComCartao pagamento;

        if (PagamentoRepository.count() == 0){
            pagamento = new PagamentoComCartao((long)1, Estado.valueOf(estadoPagamento), LocalDateTime.now());
        }else{
            List<Pagamento> pagamentos = PagamentoRepository.findAll();
            long max_id = 0;
            for (int i = 0; i < pagamentos.size(); i++){
                long id = pagamentos.get(i).getId();

                if (id > max_id){
                    max_id = pagamentos.get(i).getId();
                }
            }
            pagamento = new PagamentoComCartao(max_id, Estado.valueOf(estadoPagamento), LocalDateTime.now());
        }

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }
        pedido.setPagamento(pagamento);

        return "Pagamento definido";
    }

    @GetMapping("/set_pagamento_dinheiro")
    public String set_pagamento_dinheiro(@RequestParam() long id_pedido, String estadoPagamento) {
        Pedido pedido;
        PagamentoComDinheiro pagamento;

        if (PagamentoRepository.count() == 0){
            pagamento = new PagamentoComDinheiro((long)1, Estado.valueOf(estadoPagamento), LocalDateTime.now());
        }else{
            List<Pagamento> pagamentos = PagamentoRepository.findAll();
            long max_id = 0;
            for (int i = 0; i < pagamentos.size(); i++){
                long id = pagamentos.get(i).getId();

                if (id > max_id){
                    max_id = pagamentos.get(i).getId();
                }
            }
            pagamento = new PagamentoComDinheiro(max_id, Estado.valueOf(estadoPagamento), LocalDateTime.now());
        }

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }
        pedido.setPagamento(pagamento);

        return "Pagamento definido";
    }

    @GetMapping("/fechar_pedido")
    public String fechar_pedido(@RequestParam() long id_pedido) {
        Pedido pedido;

        try{
            pedido = PedidoRepository.findById(id_pedido).orElse(null);

        }catch (Exception e){
            System.out.println(e);

            return null;
        }
        pedido.fecharPedido();

        return "Pedido finalizado";
    }
}
