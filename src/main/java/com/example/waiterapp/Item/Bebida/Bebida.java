package com.example.waiterapp.Item.Bebida;

import com.example.waiterapp.Item.Item;

import java.time.LocalDateTime;

public class Bebida extends Item {
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
                "quantidade='" + quantidade + '\'' +
                super.toString() +
                '}';
    }
}
