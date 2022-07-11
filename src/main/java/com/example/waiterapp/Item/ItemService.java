package com.example.waiterapp.Item;

import com.example.waiterapp.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    
    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    public Item transformarDTO(ItemDTO itemDTO){
        Item item = new Item(itemDTO.getId(), itemDTO.getNome(), itemDTO.getDescricao(), itemDTO.getDataCriacao(), itemDTO.getPreco());
        item.setCardapios(itemDTO.getCardapios());
        item.setItems(itemDTO.getItems());

        return item;
    }
}
