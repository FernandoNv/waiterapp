package com.example.waiterapp.Garcom;

import com.example.waiterapp.Pedido.Pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Garcom {
    private Long id;
    private String nome;
    private LocalDateTime dataCriacao;
    private String cpf;

    private List<Pedido> pedidos = new ArrayList<>();

    public Garcom() {
    }

    public Garcom(Long id, String nome, LocalDateTime dataCriacao, String cpf) {
        this.id = id;
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Garcom garcom = (Garcom) o;
        return Objects.equals(id, garcom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Garcom{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
