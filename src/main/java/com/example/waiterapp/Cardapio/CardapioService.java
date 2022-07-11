package com.example.waiterapp.Cardapio;

import com.example.waiterapp.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CardapioService {

    private CardapioRepository cardapioRepository;

    @Autowired
    public CardapioService(CardapioRepository cardapioRepository) {
        this.cardapioRepository = cardapioRepository;
    }

    public List<Cardapio> listaCardapios(){
        return cardapioRepository.findAll();
    }

    public Cardapio transformarDTO(CardapioDTO cardapioDTO){
        Cardapio cardapio = new Cardapio(cardapioDTO.getId(), cardapioDTO.getDataCriacao(), cardapioDTO.getTitulo(), cardapioDTO.getDescricao());
        cardapio.setItems(cardapioDTO.getItems());
        return cardapio;
    }

    public Cardapio retornaCardapioById(Long idCardapio) throws ObjectNotFoundException{
        Optional<Cardapio> cardapio = cardapioRepository.findById(idCardapio);
        return cardapio.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! ID: " + idCardapio + ", Tipo: " + Cardapio.class.getName()));
    }

    public Cardapio insereCardapio(Cardapio cardapio){
        cardapio.setId(null);
        cardapio.setDataCriacao(LocalDateTime.now());
        return cardapioRepository.save(cardapio);
    }

    public Cardapio atualizaCardapio(Cardapio cardapio) throws ObjectNotFoundException{
        retornaCardapioById(cardapio.getId());
        return cardapioRepository.save(cardapio);
    }

    public void apagaCardapio(Long idCardapio) throws DataIntegrityViolationException, ObjectNotFoundException{
        retornaCardapioById(idCardapio);
        try{
            cardapioRepository.deleteById(idCardapio);
        }catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(("Não é possível excluir esse cardapio"));
        }
    }
}
