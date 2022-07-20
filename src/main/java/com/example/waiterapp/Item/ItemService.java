package com.example.waiterapp.Item;

import com.example.waiterapp.Item.Bebida.BebidaRepository;
import com.example.waiterapp.Item.Prato.PratoRepository;
import com.example.waiterapp.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    public enum TipoItem{
        Prato,
        Bebida
    }
    
    private ItemRepository itemRepository;
    private BebidaRepository bebidaRepository;
    private PratoRepository pratoRepository;

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

    public List<Item> listaItens(){
        return itemRepository.findAll();
    }

    public Item retornaItemById(Long idItem) throws ObjectNotFoundException{
        Optional<Item> item = itemRepository.findById(idItem);
        return item.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! ID: " + idItem + ", Tipo: " + Item.class.getName()));
    }

    public Item insereItem(Item item){
        item.setId(null);
        item.setDataCriacao(LocalDateTime.now());

        return itemRepository.save(item);
    }

    public Item atualizaItem(Item item) throws ObjectNotFoundException{
        retornaItemById(item.getId());

        return itemRepository.save(item);
    }

    public void apagaItem(Long idItem) throws DataIntegrityViolationException, ObjectNotFoundException {
        retornaItemById(idItem);
        try{
            itemRepository.deleteById(idItem);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException(("Não é possível excluir esse item"));
        }
    }

}
