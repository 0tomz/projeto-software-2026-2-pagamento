package br.insper.pagamento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * EXERCÍCIO: Implementar o Padrão Observable para Notificações de Pagamento
 *
 * OBJETIVO:
 * Implementar o padrão Observer/Observable para notificar diferentes componentes
 * quando o status de um pagamento é criado ou quando muda (pendente → processado).
 *
 * REQUISITOS:
 *
 * 1. CRIAR INTERFACES:
 *    - PagamentoObserver: interface que define o contrato para observers
 *      - Método: void atualizar(Pagamento pagamento, String statusAnterior, String statusNovo)
 *
 *    - PagamentoObservable: interface que define o contrato para o subject
 *      - Método: void registrarObservador(PagamentoObserver observer)
 *      - Método: void notificarObservadores(Pagamento pagamento, String statusAnterior)
 *
 * 2. IMPLEMENTAR OBSERVERS (pelo menos 2):
 *
 *    a) AuditLoggerObserver:
 *       - Registra em log todas as mudanças de status
 *       - Deve imprimir: "AUDITORIA - Pagamento ID: {id} | Status: {anterior} → {novo} | Valor: {valor}"
 *       - Use @Component do Spring
 *
 *    b) EmailNotifierObserver:
 *       - Simula envio de email quando status muda
 *       - Deve imprimir: "EMAIL ENVIADO - Pagamento ID: {id} mudou para status: {novo}"
 *       - Use @Component do Spring
 *
 *    c) (OPCIONAL) Crie mais um observer de sua escolha (SMS, Webhook, etc)
 *
 * 3. IMPLEMENTAR PAGAMENTOSERVICE COM OBSERVABLE:
 *    - PagamentoService deve implementar PagamentoObservable
 *    - No método @PostConstruct, registre todos os observers autowired
 *    - Nos métodos que mudam status (criar, processar), chame notificarObservadores()
 *    - Mantenha uma lista thread-safe de observers (use CopyOnWriteArrayList)
 *
 * BENEFÍCIOS:
 * ✓ Desacoplamento: observers não conhecem PagamentoService
 * ✓ Extensibilidade: adicione novos observers sem modificar o serviço
 * ✓ Responsabilidade única: cada observer tem uma responsabilidade
 * ✓ Testabilidade: fácil mockar observers para testes
 *
 */

@SpringBootApplication
public class PagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagamentoApplication.class, args);
	}

}
