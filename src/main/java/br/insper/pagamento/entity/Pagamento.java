package br.insper.pagamento.entity;

import br.insper.pagamento.dto.PagamentoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagamentos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipo;

	@Column(nullable = false)
	private BigDecimal valor;

	@Column(nullable = false)
	private Integer parcelas;

	@Column(nullable = false)
	private LocalDate dataCompra;

	@Column
	private LocalDate dataPagamento;

	@Column
	private String status;

	// Campos PIX
	@Column
	private String chaveOrigem;

	@Column
	private String chaveDestino;

	// Campos Cartão de Crédito
	@Column
	private String numeroCartao;

	@Column
	private String dataValidade;

	@Column
	private String cvc;

	@Column
	private String codigoBarras;

	public static Pagamento fromDto(PagamentoDto dto) {
		Pagamento pagamento = new Pagamento();
		pagamento.setTipo(dto.getTipo());
		pagamento.setValor(dto.getValor());
		pagamento.setParcelas(dto.getParcelas());
		pagamento.setDataCompra(dto.getDataCompra());
		pagamento.setChaveOrigem(dto.getChaveOrigem());
		pagamento.setChaveDestino(dto.getChaveDestino());
		pagamento.setNumeroCartao(dto.getNumeroCartao());
		pagamento.setDataValidade(dto.getDataValidade());
		pagamento.setCvc(dto.getCvc());
		pagamento.setStatus("pendente");
		return pagamento;
	}
}
