package com.example.waiterapp.Garcom;

import com.example.waiterapp.Pedido.Pedido;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GarcomDTO implements Serializable {

    private Long id;
    private String nome;
    private LocalDateTime dataCriacao;
    private String cpf;
    private List<Pedido> pedidos = new ArrayList<>();

    public GarcomDTO() {
    }

    public GarcomDTO(Garcom garcom) {
        this.id = garcom.getId();
        this.nome = garcom.getNome();
        this.dataCriacao = garcom.getDataCriacao();
        this.cpf = garcom.getCpf();
        this.pedidos = garcom.getPedidos();
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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
