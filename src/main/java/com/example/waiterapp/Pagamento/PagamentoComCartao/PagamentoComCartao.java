package com.example.waiterapp.Pagamento.PagamentoComCartao;

import com.example.waiterapp.Pagamento.IPagamento;
import com.example.waiterapp.Pagamento.Pagamento;
import com.example.waiterapp.enums.Estado;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class PagamentoComCartao extends Pagamento implements IPagamento {

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Long id, Estado estadoPagamento, LocalDateTime dataPagamento) {
        super(id, estadoPagamento, dataPagamento);
    }

    @Override
    public Integer confirmarPagamento() {
        //Todo: Simular um pagamento com cartao
        return null;
    }

}
