package br.insper.pagamento.validator;

import br.insper.pagamento.dto.PagamentoDto;
import br.insper.pagamento.entity.TipoPagamento;
import br.insper.pagamento.exception.ValidacaoPagamentoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPagamento {

	public void validarCamposObrigatorios(PagamentoDto dto) {
		if (dto.getTipo() == null) {
			throw new ValidacaoPagamentoException("Tipo de pagamento é obrigatório");
		}
		if (dto.getValor() == null || dto.getValor().signum() <= 0) {
			throw new ValidacaoPagamentoException("Valor deve ser maior que zero");
		}
		if (dto.getDataCompra() == null) {
			throw new ValidacaoPagamentoException("Data de compra é obrigatória");
		}
	}

	public void validarBoleto(PagamentoDto dto) {
		if (dto.getTipo() != TipoPagamento.BOLETO) {
			throw new ValidacaoPagamentoException("Tipo de pagamento deve ser BOLETO");
		}
		if (dto.getParcelas() == null || dto.getParcelas() <= 0) {
			throw new ValidacaoPagamentoException("Número de parcelas deve ser maior que zero para pagamentos com BOLETO");
		}
	}

	public void validarCredito(PagamentoDto dto) {
		if (dto.getTipo() != TipoPagamento.CREDITO) {
			throw new ValidacaoPagamentoException("Tipo de pagamento deve ser CREDITO");
		}
		if (dto.getNumeroCartao() == null || dto.getNumeroCartao().isBlank()) {
			throw new ValidacaoPagamentoException("Número do cartão é obrigatório para pagamentos com CREDITO");
		}
		if (dto.getDataValidade() == null || dto.getDataValidade().isBlank()) {
			throw new ValidacaoPagamentoException("Data de validade é obrigatória para pagamentos com CREDITO");
		}
		if (dto.getCvc() == null || dto.getCvc().isBlank()) {
			throw new ValidacaoPagamentoException("CVC é obrigatório para pagamentos com CREDITO");
		}
		if (dto.getParcelas() == null || dto.getParcelas() <= 0) {
			throw new ValidacaoPagamentoException("Número de parcelas deve ser maior que zero para pagamentos com CREDITO");
		}
	}



	public void validarPix(PagamentoDto dto) {
		if (dto.getTipo() != TipoPagamento.PIX) {
			throw new ValidacaoPagamentoException("Tipo de pagamento deve ser PIX");
		}
		if (dto.getChaveOrigem() == null || dto.getChaveOrigem().isBlank()) {
			throw new ValidacaoPagamentoException("Chave de origem é obrigatória para pagamentos PIX");
		}
		if (dto.getChaveDestino() == null || dto.getChaveDestino().isBlank()) {
			throw new ValidacaoPagamentoException("Chave de destino é obrigatória para pagamentos PIX");
		}
	}
}
