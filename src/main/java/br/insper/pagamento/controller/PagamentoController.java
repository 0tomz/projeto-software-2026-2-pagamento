package br.insper.pagamento.controller;

import br.insper.pagamento.dto.PagamentoDto;
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
		try {
			boolean sucesso = pagamentoService.processar(id);
			Map<String, Object> resposta = new HashMap<>();
			resposta.put("sucesso", sucesso);
			resposta.put("mensagem", sucesso ? "Pagamento processado com sucesso" : "Erro ao processar pagamento");
			return ResponseEntity.ok(resposta);
		} catch (ValidacaoPagamentoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroResponse(e.getMessage()));
		}
	}

	private Map<String, String> erroResponse(String mensagem) {
		Map<String, String> erro = new HashMap<>();
		erro.put("erro", mensagem);
		return erro;
	}
}
