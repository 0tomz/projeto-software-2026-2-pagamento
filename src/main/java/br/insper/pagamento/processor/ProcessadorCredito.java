package br.insper.pagamento.processor;

import br.insper.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProcessadorCredito {

	public boolean processarCredito(Pagamento pagamento) {
		try {
			System.out.println("Processando pagamento com Cartão de Crédito...");
			System.out.println("Número do Cartão: " + mascararCartao(pagamento.getNumeroCartao()));
			System.out.println("Parcelamento: " + pagamento.getParcelas() + "x");
			System.out.println("Valor total: " + pagamento.getValor());

			// Simulação de processamento
			validarCartao(pagamento);
			validarFundosDisponiveis(pagamento);
			procesarParcelamento(pagamento);

			pagamento.setStatus("processado");
			pagamento.setDataPagamento(LocalDate.now());

			System.out.println("Cartão de crédito processado com sucesso!");
			return true;
		} catch (Exception e) {
			pagamento.setStatus("falha");
			System.out.println("Erro ao processar cartão de crédito: " + e.getMessage());
			return false;
		}
	}

	private void validarCartao(Pagamento pagamento) {
		if (pagamento.getNumeroCartao() == null || pagamento.getNumeroCartao().length() != 16) {
			throw new RuntimeException("Número de cartão inválido");
		}
		if (pagamento.getDataValidade() == null || pagamento.getDataValidade().isBlank()) {
			throw new RuntimeException("Data de validade inválida");
		}
		if (pagamento.getCvc() == null || pagamento.getCvc().length() != 3) {
			throw new RuntimeException("CVC inválido");
		}
		System.out.println("Cartão validado!");
	}

	private void validarFundosDisponiveis(Pagamento pagamento) {
		System.out.println("Validando limite de crédito disponível...");
		// Lógica de validação
		System.out.println("Limite suficiente!");
	}

	private void procesarParcelamento(Pagamento pagamento) {
		BigDecimal valorParcela = pagamento.getValor().divide(BigDecimal.valueOf(pagamento.getParcelas()));
		System.out.println("Valor por parcela: " + valorParcela);
		System.out.println("Processando " + pagamento.getParcelas() + " parcelas...");
		// Lógica de parcelamento
		System.out.println("Parcelamento processado!");
	}

	private String mascararCartao(String numeroCartao) {
		if (numeroCartao == null || numeroCartao.length() < 4) {
			return "****";
		}
		return "**** **** **** " + numeroCartao.substring(12);
	}
}
