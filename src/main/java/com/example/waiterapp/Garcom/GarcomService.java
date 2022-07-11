package com.example.waiterapp.Garcom;

import com.example.waiterapp.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GarcomService {

    private GarcomRepository garcomRepository;

    @Autowired
    public GarcomService(GarcomRepository garcomRepository) {
        this.garcomRepository = garcomRepository;
    }

    public Garcom transformarDTO(GarcomDTO garcomDTO){
        Garcom garcom = new Garcom(garcomDTO.getId(), garcomDTO.getNome(), garcomDTO.getDataCriacao(), garcomDTO.getCpf());
        garcom.setPedidos(garcomDTO.getPedidos());
        return garcom;
    }

    public List<Garcom> listaGarcons(){
        return garcomRepository.findAll();
    }

    public Garcom retornaGarcomById(Long idGarcom) throws ObjectNotFoundException {
        Optional<Garcom> garcom = garcomRepository.findById(idGarcom);
        return garcom.orElseThrow(() -> new ObjectNotFoundException("Objeto nao encontrado! ID: " + idGarcom + ", Tipo: " + Garcom.class.getName()));
    }

    public Garcom insereGarcom(Garcom garcom){
        garcom.setId(null);
        garcom.setDataCriacao(LocalDateTime.now());
        return garcomRepository.save(garcom);
    }

    public Garcom atualizaGarcom(Garcom garcom) throws ObjectNotFoundException{
        retornaGarcomById(garcom.getId());
        return garcomRepository.save(garcom);
    }

    public void apagaGarcom(Long idGarcom) throws DataIntegrityViolationException, ObjectNotFoundException{
        retornaGarcomById(idGarcom);
        try{
            garcomRepository.deleteById(idGarcom);
        }catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(("Não é possível excluir esse garcom"));
        }
    }
}
