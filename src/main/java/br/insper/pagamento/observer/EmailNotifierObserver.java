package br.insper.pagamento.observer;

import br.insper.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class EmailNotifierObserver implements PagamentoObserver {

    @Override
    public void atualizar(Pagamento pagamento, String statusAnterior, String statusNovo) {
        String mensagem = String.format(
            "EMAIL ENVIADO - Pagamento ID: %d mudou para status: %s",
            pagamento.getId(),
            statusNovo
        );
        System.out.println(mensagem);
    }
}
