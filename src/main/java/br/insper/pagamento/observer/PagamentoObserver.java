package br.insper.pagamento.observer;

import br.insper.pagamento.entity.Pagamento;

public interface PagamentoObserver {
    void atualizar(Pagamento pagamento, String statusAnterior, String statusNovo);
}
