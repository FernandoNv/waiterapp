package com.example.waiterapp.Item.Prato;

import com.example.waiterapp.Ingrediente.Ingrediente;
import com.example.waiterapp.Item.Item;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Prato extends Item {

    @ManyToMany
    @JoinTable(
            name = "PRATO_INGREDIENTE",
            joinColumns = @JoinColumn(name = "fk_prato"),
            inverseJoinColumns = @JoinColumn(name = "fk_ingrediente")
    )
    @OrderBy("nome asc")
    private List<Ingrediente> ingredientes = new ArrayList<>();

    public Prato() {
    }

    public Prato(Long id, String nome, String descricao, LocalDateTime dataCriacao, Double preco) {
        super(id, nome, descricao, dataCriacao, preco);
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Float getCaloriaTotal(){
        return ingredientes.stream().map(Ingrediente::getCaloria).reduce(0F, Float::sum);
    }
}
