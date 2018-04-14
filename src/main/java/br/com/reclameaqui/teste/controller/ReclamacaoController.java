package br.com.reclameaqui.teste.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.reclameaqui.teste.dtos.ReclamacaoDTO;
import br.com.reclameaqui.teste.exceptions.ReclamacaoNaoEncontradaException;
import br.com.reclameaqui.teste.service.ReclamacaoService;
import br.com.reclameaqui.teste.service.ReclamacaoServiceImpl;

@RestController
@RequestMapping("")
public class ReclamacaoController {

	private ReclamacaoService reclamacaoService;

	@Autowired
	public ReclamacaoController(ReclamacaoService reclamacaoService) {
		this.reclamacaoService = reclamacaoService;
	}
	
	private final Logger logger = Logger.getLogger(ReclamacaoServiceImpl.class);

	@GetMapping("/reclamacoes")
	public ResponseEntity<List<ReclamacaoDTO>> buscarTodasReclamacoes() {

		List<ReclamacaoDTO> listReclamacoes = reclamacaoService.buscarTodos();
		
		return new ResponseEntity<List<ReclamacaoDTO>>(listReclamacoes, HttpStatus.OK);
	}

	@GetMapping("/reclamacao/{idReclamacao}")
	public ResponseEntity<ReclamacaoDTO> consultar(@PathVariable(value = "idReclamacao") String idReclamacao) {
		logger.info("Rest consultar reclamacao");
		
		ReclamacaoDTO reclamacaoDTO;
		try {
			reclamacaoDTO = reclamacaoService.consultar(idReclamacao);

		} catch (ReclamacaoNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<ReclamacaoDTO>(reclamacaoDTO, HttpStatus.OK);
	}

	@PostMapping("/reclamacao")
	public ResponseEntity<ReclamacaoDTO> incluir(@RequestBody ReclamacaoDTO reclamacaoDTO) {
		logger.info("Rest incluir reclamacao");

		ReclamacaoDTO responseReclamacaoDTO = reclamacaoService.incluir(reclamacaoDTO);
		return new ResponseEntity<ReclamacaoDTO>(responseReclamacaoDTO, HttpStatus.CREATED);
	}

	@PutMapping("/reclamacao/{id}")
	public ResponseEntity<ReclamacaoDTO> atualizar(@PathVariable(value = "id") String id,
			@RequestBody @Valid ReclamacaoDTO reclamacaoDTODetails) {
		logger.info("Rest atualizar reclamacao");

		ReclamacaoDTO reclamacaoDTO;
		try {
			reclamacaoDTO = reclamacaoService.atualizar(id, reclamacaoDTODetails);
		} catch (ReclamacaoNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}

		return new ResponseEntity<ReclamacaoDTO>(reclamacaoDTO, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/reclamacao/{id}")
	public ResponseEntity<ReclamacaoDTO> deletar(@PathVariable(value = "id") String id) {
		logger.info("Rest deletar reclamacao");
		
		ResponseEntity<ReclamacaoDTO> response;
		try {
			response = reclamacaoService.deletar(id);
		} catch (ReclamacaoNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}

		
		return response;
	}

}