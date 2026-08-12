package br.insper.pagamento.processor;

import br.insper.pagamento.entity.Pagamento;

public interface Processador {

    boolean processar(Pagamento pagamento);

}
