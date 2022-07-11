package com.example.waiterapp.Cardapio;

import com.example.waiterapp.Item.Item;
// import com.fasterxml.jackson.annotation.JsonIdentityInfo;
// import com.fasterxml.jackson.annotation.JsonManagedReference;
// import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cardapio implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCriacao;
    @Column(nullable = false)
    private String titulo;
    @Column(length = 2000)
    private String descricao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CARDAPIO_ITEM",
            joinColumns = @JoinColumn(name = "fk_cardapio"),
            inverseJoinColumns = @JoinColumn(name = "fk_item")
    )
    private List<Item> items = new ArrayList<>();

    public Cardapio() {}

    public Cardapio(Long id, LocalDateTime dataCriacao, String titulo, String descricao) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cardapio cardapio = (Cardapio) o;
        return Objects.equals(id, cardapio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cardapio{" +
                "id=" + id +
                ", dataCriacao=" + dataCriacao +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
