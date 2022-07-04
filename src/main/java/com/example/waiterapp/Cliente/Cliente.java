package com.example.waiterapp.Cliente;

import java.time.LocalDate;
import java.util.Objects;

public class Cliente {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataCriação;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String cpf, LocalDate dataCriação) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataCriação = dataCriação;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataCriação() {
        return dataCriação;
    }

    public void setDataCriação(LocalDate dataCriação) {
        this.dataCriação = dataCriação;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataCriação=" + dataCriação +
                '}';
    }
}
