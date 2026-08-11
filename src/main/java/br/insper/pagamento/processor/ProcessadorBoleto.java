package br.insper.pagamento.processor;

import br.insper.pagamento.entity.Pagamento;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class ProcessadorBoleto {

	public boolean processarBoleto(Pagamento pagamento) {
		try {
			System.out.println("Processando pagamento com Boleto Bancário...");
			System.out.println("Número de parcelas: " + pagamento.getParcelas());
			System.out.println("Valor: " + pagamento.getValor());

			// Simulação de processamento
			gerarBoletos(pagamento);
			registrarBoletos(pagamento);

			pagamento.setStatus("processado");
			pagamento.setDataPagamento(LocalDate.now());

			System.out.println("Boleto(s) gerado(s) com sucesso!");
			return true;
		} catch (Exception e) {
			pagamento.setStatus("falha");
			System.out.println("Erro ao processar boleto: " + e.getMessage());
			return false;
		}
	}

	private void gerarBoletos(Pagamento pagamento) {
		System.out.println("Gerando " + pagamento.getParcelas() + " boleto(s)...");
		for (int i = 1; i <= pagamento.getParcelas(); i++) {
			String numeroBoleto = gerarNumeroBoleto();
			LocalDate dataVencimento = calcularDataVencimento(pagamento, i);
			System.out.println("Boleto " + i + " - Número: " + numeroBoleto + " - Vencimento: " + dataVencimento);
		}
		String codigoBarras = gerarCodigoBarras();
		pagamento.setCodigoBarras(codigoBarras);
		System.out.println("Código de Barras: " + codigoBarras);
		System.out.println("Boleto(s) gerado(s)!");
	}

	private void registrarBoletos(Pagamento pagamento) {
		System.out.println("Registrando boleto(s) no banco...");
		// Lógica de registro
		System.out.println("Boleto(s) registrado(s)!");
	}

	private String gerarNumeroBoleto() {
		long numero = System.currentTimeMillis();
		return String.format("%20d", numero).replace(" ", "0");
	}

	private String gerarCodigoBarras() {
		StringBuilder codigoBarras = new StringBuilder();
		codigoBarras.append("12345");
		for (int i = 0; i < 40; i++) {
			codigoBarras.append((int) (Math.random() * 10));
		}
		codigoBarras.append("12345");
		return codigoBarras.toString();
	}

	private LocalDate calcularDataVencimento(Pagamento pagamento, int parcela) {
		return pagamento.getDataCompra().plusMonths(parcela);
	}
}
