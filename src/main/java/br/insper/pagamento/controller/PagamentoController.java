package br.insper.pagamento.controller;

import br.insper.pagamento.dto.PagamentoDto;
import br.insper.pagamento.dto.RespostaPagamentoDto;
import br.insper.pagamento.entity.Pagamento;
import br.insper.pagamento.exception.ValidacaoPagamentoException;
import br.insper.pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;

	@PostMapping
	public Pagamento criar(@RequestBody PagamentoDto dto) {
		return pagamentoService.criar(dto);
	}

	@GetMapping
	public List<Pagamento> listar() {
		return pagamentoService.listarTodos();
	}

	@GetMapping("/{id}")
	public Pagamento obter(@PathVariable Long id) {
		Optional<Pagamento> pagamento = pagamentoService.obterPorId(id);
		if (pagamento.isPresent()) {
			return pagamento.get();
		}
		return null;
	}

	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		pagamentoService.deletar(id);
	}

	@PostMapping("/{id}/processar")
	public ResponseEntity<?> processar(@PathVariable Long id) {
		boolean sucesso = pagamentoService.processar(id);
		RespostaPagamentoDto resposta = new RespostaPagamentoDto(
			sucesso,
			sucesso ? "Pagamento processado com sucesso" : "Erro ao processar pagamento"
		);
		return ResponseEntity.ok(resposta);
	}

}
