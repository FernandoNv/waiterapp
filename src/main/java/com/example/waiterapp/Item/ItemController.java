package com.example.waiterapp.Item;

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
@RequestMapping({"/api/itens"})
public class ItemController {
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Item>> listaItens(){
        List<Item> itens = itemService.listaItens();

        return ResponseEntity.ok().body(itens);
    }

    @GetMapping(value = "/{idItem}", produces = "application/json")
    public ResponseEntity<Item> retornItemById(@PathVariable Long idItem){
        try{
            Item item = itemService.retornaItemById(idItem);

            return ResponseEntity.ok().body(item);
        }catch (ObjectNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<Void> insereItem(@Valid @RequestBody ItemDTO itemDTO){
        Item item = itemService.transformarDTO(itemDTO);
        item = itemService.insereItem(item);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(item.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{idItem}", consumes = "application/json")
    public ResponseEntity<Void> atualizaItem(@Valid @RequestBody ItemDTO itemDTO, @PathVariable Long idItem){
        Item item = itemService.transformarDTO(itemDTO);

        item.setId(idItem);
        try{
            itemService.atualizaItem(item);
            return ResponseEntity.ok().build();
        }catch (ObjectNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(value = "/{idItem}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long idItem){
        try{
            itemService.apagaItem(idItem);
            return ResponseEntity.noContent().build();
        }catch (DataIntegrityViolationException | ObjectNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
