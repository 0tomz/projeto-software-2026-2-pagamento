package br.insper.pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespostaPagamentoDto {
	private Boolean sucesso;
	private String mensagem;
}
