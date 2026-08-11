package br.insper.pagamento.service;

import br.insper.pagamento.dto.PagamentoDto;
import br.insper.pagamento.entity.Pagamento;
import br.insper.pagamento.entity.TipoPagamento;
import br.insper.pagamento.exception.ValidacaoPagamentoException;
import br.insper.pagamento.processor.ProcessadorBoleto;
import br.insper.pagamento.processor.ProcessadorCredito;
import br.insper.pagamento.processor.ProcessadorPix;
import br.insper.pagamento.repository.PagamentoRepository;
import br.insper.pagamento.validator.ValidadorPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;

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
		Optional<Pagamento> pagamentoOpt = pagamentoRepository.findById(id);
		if (pagamentoOpt.isEmpty()) {
			throw new ValidacaoPagamentoException("Pagamento com ID " + id + " não encontrado");
		}

		Pagamento pagamento = pagamentoOpt.get();
		boolean sucesso;

		if (pagamento.getTipo() == TipoPagamento.PIX) {
			ProcessadorPix processadorPix = new ProcessadorPix();
			sucesso = processadorPix.processar(pagamento);
		} else if (pagamento.getTipo() == TipoPagamento.CREDITO) {
			ProcessadorCredito processadorCredito = new ProcessadorCredito();
			sucesso = processadorCredito.processar(pagamento);
		} else if (pagamento.getTipo() == TipoPagamento.BOLETO) {
			ProcessadorBoleto processadorBoleto = new ProcessadorBoleto();
			sucesso = processadorBoleto.processar(pagamento);
		} else {
			throw new ValidacaoPagamentoException("Tipo de pagamento desconhecido: " + pagamento.getTipo());
		}

		pagamentoRepository.save(pagamento);
		return sucesso;
	}

	private void validarPagamento(PagamentoDto dto) {
		ValidadorPagamento validadorPagamentoBase = new ValidadorPagamento();
		validadorPagamentoBase.validarCamposObrigatorios(dto);

		if (dto.getTipo() == TipoPagamento.PIX) {
			validadorPix.validar(dto);
		} else if (dto.getTipo() == TipoPagamento.CREDITO) {
			validadorCredito.validar(dto);
		} else if (dto.getTipo() == TipoPagamento.BOLETO) {
			validadorBoleto.validar(dto);
		}
	}
}
