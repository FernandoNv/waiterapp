package com.example.waiterapp.Item.Bebida;

import com.example.waiterapp.Item.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Bebida extends Item {
    @Column(nullable = false)
    private String quantidade;

    public Bebida() {
    }

    public Bebida(Long id, String nome, String descricao, LocalDateTime dataCriacao, Double preco, String quantidade) {
        super(id, nome, descricao, dataCriacao, preco);
        this.quantidade = quantidade;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Bebida{" +
                super.toString() +
                "quantidade='" + quantidade + '\'' +
                '}';
    }
}
