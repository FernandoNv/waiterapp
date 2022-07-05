package com.example.waiterapp.Pagamento;

import com.example.waiterapp.enums.Estado;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class Pagamento {
    private Long id;
    private Estado estadoPagamento;
    private LocalDateTime dataPagamento;

    public Pagamento() {
    }

    public Pagamento(Long id, Estado estadoPagamento, LocalDateTime dataPagamento) {
        this.id = id;
        this.estadoPagamento = estadoPagamento;
        this.dataPagamento = dataPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Estado getEstadoPagamento() {
        return estadoPagamento;
    }

    public void setEstadoPagamento(Estado estadoPagamento) {
        this.estadoPagamento = estadoPagamento;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", estadoPagamento=" + estadoPagamento +
                ", dataPagamento=" + dataPagamento +
                '}';
    }
}
