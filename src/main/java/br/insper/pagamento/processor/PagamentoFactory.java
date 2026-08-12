package br.insper.pagamento.processor;

import br.insper.pagamento.entity.TipoPagamento;
import br.insper.pagamento.exception.ValidacaoPagamentoException;

public class PagamentoFactory {

    public Processador getProcessador(TipoPagamento tipoPagamento) {
        if (tipoPagamento == TipoPagamento.PIX) {
            return new ProcessadorPix();
        } else if (tipoPagamento == TipoPagamento.CREDITO) {
            return new ProcessadorCredito();
        } else if (tipoPagamento == TipoPagamento.BOLETO) {
            return new ProcessadorBoleto();
        } else {
            throw new ValidacaoPagamentoException("Tipo de pagamento desconhecido: " + tipoPagamento);
        }
    }


}
