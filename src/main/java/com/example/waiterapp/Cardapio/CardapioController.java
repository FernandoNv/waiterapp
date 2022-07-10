package com.example.waiterapp.Cardapio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping({"/cardapios"})
public class CardapioController {

    private CardapioService cardapioService;

    @Autowired
    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @GetMapping
    public ResponseEntity<List<Cardapio>> listaCardapios() {
        List<Cardapio> cardapios = cardapioService.listaCardapios();
        return ResponseEntity.ok().body(cardapios);
    }

    @GetMapping(value = "/{idCardapio}")
    public ResponseEntity<Cardapio> retornaCardapioById(@PathVariable Long idCardapio){
        Cardapio cardapio = cardapioService.retornaCardapioById(idCardapio);

        return ResponseEntity.ok().body(cardapio);
    }

    @PostMapping
    public ResponseEntity<Void> insereCardapio(@Valid @RequestBody CardapioDTO cardapioDTO){
        Cardapio cardapio = cardapioService.transformarDTO(cardapioDTO);
        cardapio = cardapioService.insereCardapio(cardapio);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cardapio.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{idCardapio}")
    public ResponseEntity<Void> atualizaCardapio(@Valid @RequestBody CardapioDTO cardapioDTO, @PathVariable Long idCardapio){
        Cardapio cardapio = cardapioService.transformarDTO(cardapioDTO);

        cardapio.setId(idCardapio);
        cardapioService.atualizaCardapio(cardapio);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{idCardapio}")
    public ResponseEntity<Void> deleteCardapio(@PathVariable Long idCardapio){
        cardapioService.apagaCardapio(idCardapio);

        return ResponseEntity.noContent().build();
    }
}
