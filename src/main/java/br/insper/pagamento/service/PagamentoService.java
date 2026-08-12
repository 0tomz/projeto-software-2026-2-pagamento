package br.insper.pagamento.service;

import br.insper.pagamento.dto.PagamentoDto;
import br.insper.pagamento.entity.Pagamento;
import br.insper.pagamento.entity.TipoPagamento;
import br.insper.pagamento.exception.ValidacaoPagamentoException;
import br.insper.pagamento.processor.*;
import br.insper.pagamento.repository.PagamentoRepository;
import br.insper.pagamento.validator.ValidadorPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private Map<String, Processador> processadores;

	public Pagamento criar(PagamentoDto dto) {
		validarPagamento(dto);
		Pagamento pagamento = Pagamento.fromDto(dto);
		return pagamentoRepository.save(pagamento);
	}

	public List<Pagamento> listarTodos() {
		return pagamentoRepository.findAll();
	}

	public Optional<Pagamento> obterPorId(Long id) {
		return pagamentoRepository.findById(id);
	}

	public boolean deletar(Long id) {
		if (pagamentoRepository.existsById(id)) {
			pagamentoRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public boolean processar(Long id) {
		Pagamento pagamento = pagamentoRepository
				.findById(id)
				.orElseThrow(() -> new ValidacaoPagamentoException("Pagamento com ID " + id + " não encontrado"));

		Processador processador = processadores.get(pagamento.getTipo().toString());
		boolean sucesso = processador.processar(pagamento);

		pagamentoRepository.save(pagamento);

		return sucesso;
	}

	private void validarPagamento(PagamentoDto dto) {
		ValidadorPagamento validadorPagamentoBase = new ValidadorPagamento();
		validadorPagamentoBase.validarCamposObrigatorios(dto);

		if (dto.getTipo() == TipoPagamento.PIX) {
			validadorPagamentoBase.validarPix(dto);
		} else if (dto.getTipo() == TipoPagamento.CREDITO) {
			validadorPagamentoBase.validarCredito(dto);
		} else if (dto.getTipo() == TipoPagamento.BOLETO) {
			validadorPagamentoBase.validarBoleto(dto);
		}
	}
}
