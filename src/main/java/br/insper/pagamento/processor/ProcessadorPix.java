package br.insper.pagamento.processor;

import br.insper.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class ProcessadorPix {

	public boolean processarPix(Pagamento pagamento) {
		try {
			System.out.println("Processando pagamento PIX...");
			System.out.println("Chave Origem: " + pagamento.getChaveOrigem());
			System.out.println("Chave Destino: " + pagamento.getChaveDestino());
			System.out.println("Valor: " + pagamento.getValor());

			// Simulação de processamento
			validarChaves(pagamento);
			transferirFundos(pagamento);

			pagamento.setStatus("processado");
			pagamento.setDataPagamento(LocalDate.now());

			System.out.println("PIX processado com sucesso!");
			return true;
		} catch (Exception e) {
			pagamento.setStatus("falha");
			System.out.println("Erro ao processar PIX: " + e.getMessage());
			return false;
		}
	}

	private void validarChaves(Pagamento pagamento) {
		if (pagamento.getChaveOrigem() == null || pagamento.getChaveOrigem().isBlank()) {
			throw new RuntimeException("Chave de origem inválida");
		}
		if (pagamento.getChaveDestino() == null || pagamento.getChaveDestino().isBlank()) {
			throw new RuntimeException("Chave de destino inválida");
		}
		System.out.println("Chaves validadas!");
	}

	private void transferirFundos(Pagamento pagamento) {
		System.out.println("Transferindo fundos via PIX...");
		// Lógica de transferência
		System.out.println("Fundos transferidos!");
	}
}
