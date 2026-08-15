package br.insper.pagamento.observer;

import br.insper.pagamento.entity.Pagamento;

public interface PagamentoObservable {
    void notificarObservadores(Pagamento pagamento, String statusAnterior);
}
