package com.example.waiterapp;

import com.example.waiterapp.Cardapio.Cardapio;
import com.example.waiterapp.Cardapio.CardapioRepository;
import com.example.waiterapp.Item.Bebida.Bebida;
import com.example.waiterapp.Item.Item;
import com.example.waiterapp.Item.ItemRepository;
import com.example.waiterapp.Item.Prato.Prato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class WaiterAppApplication implements CommandLineRunner {

    @Autowired
    private CardapioRepository cardapioRepository;

    @Autowired
    private ItemRepository itemRepository;

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
}
