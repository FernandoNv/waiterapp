package com.example.waiterapp.Cardapio;
import com.example.waiterapp.Item.Item;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CardapioDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private LocalDateTime dataCriacao;
    private String titulo;
    private String descricao;
    private List<Item> items = new ArrayList<>();

    public CardapioDTO() {
    }

    public CardapioDTO(Cardapio cardapio) {
        this.id = cardapio.getId();
        this.dataCriacao = cardapio.getDataCriacao();
        this.titulo = cardapio.getTitulo();
        this.descricao = cardapio.getDescricao();
        this.items = cardapio.getItems();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


}
