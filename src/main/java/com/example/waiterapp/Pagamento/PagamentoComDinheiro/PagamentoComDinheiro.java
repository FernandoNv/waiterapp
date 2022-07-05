package com.example.waiterapp.Pagamento.PagamentoComDinheiro;

import com.example.waiterapp.Pagamento.IPagamento;
import com.example.waiterapp.Pagamento.Pagamento;
import com.example.waiterapp.enums.Estado;

import java.time.LocalDateTime;

public class PagamentoComDinheiro extends Pagamento implements IPagamento {

    public PagamentoComDinheiro() {
    }

    public PagamentoComDinheiro(Long id, Estado estadoPagamento, LocalDateTime dataPagamento) {
        super(id, estadoPagamento, dataPagamento);
    }

    @Override
    public Integer confirmarPagamento() {
        //Todo: Simular um pagamento com dinheiro. No caso, so retornar que deu certo
        return null;
    }
}

