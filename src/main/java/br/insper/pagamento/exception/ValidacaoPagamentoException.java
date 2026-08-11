package br.insper.pagamento.exception;

public class ValidacaoPagamentoException extends RuntimeException {
	public ValidacaoPagamentoException(String mensagem) {
		super(mensagem);
	}
}
