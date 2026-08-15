package br.insper.pagamento.observer;

import br.insper.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class AuditLoggerObserver implements PagamentoObserver {

    private static final Logger logger = LoggerFactory.getLogger(AuditLoggerObserver.class);

    @Override
    public void atualizar(Pagamento pagamento, String statusAnterior, String statusNovo) {
        String mensagem = String.format(
            "AUDITORIA - Pagamento ID: %d | Status: %s → %s | Valor: %s",
            pagamento.getId(),
            statusAnterior,
            statusNovo,
            pagamento.getValor()
        );
        logger.info(mensagem);
    }
}
