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

import br.com.reclameaqui.teste.dtos.ClienteDTO;
import br.com.reclameaqui.teste.dtos.ConsultaReclamacaoRequestDTO;
import br.com.reclameaqui.teste.dtos.ConsultaResponse;
import br.com.reclameaqui.teste.exceptions.CampoNaoEncontradoException;
import br.com.reclameaqui.teste.exceptions.CampoObrigatorioException;
import br.com.reclameaqui.teste.exceptions.ClienteException;
import br.com.reclameaqui.teste.exceptions.ClienteNaoEncontradoException;
import br.com.reclameaqui.teste.service.ClienteServiceImpl;

@RestController
@RequestMapping("")
public class ClienteController {

	@Autowired
	private ClienteServiceImpl clienteService;
	private final Logger logger = Logger.getLogger(ClienteServiceImpl.class);

	@GetMapping()
	@RequestMapping("/clientes")
	public List<ClienteDTO> buscarTodasClientes() {

		return clienteService.buscarTodos();
	}

	@GetMapping()
	@RequestMapping("/teste")
	public String teste() {

		return "teste ok";
	}

	@GetMapping("/cliente/{id}")
	public ResponseEntity<ClienteDTO> consultar(@PathVariable(value = "id") String id)
			throws ClienteException, CampoNaoEncontradoException {
		logger.info("Rest consultar cliente");
		ClienteDTO clienteDTO = clienteService.consultar(id);

		return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
	}

	@GetMapping("/cliente/cpf/{cpf}")
	public ResponseEntity<ClienteDTO> consultarPorCpf(@PathVariable(value = "cpf") String cpf)
			throws ClienteException, CampoNaoEncontradoException {
		logger.info("Rest consultar cliente");
		ClienteDTO clienteDTO = clienteService.consultarPorCpf(cpf);

		return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
	}

	@PostMapping("/cliente")
	public ResponseEntity<ClienteDTO> incluir(@RequestBody @Valid ClienteDTO clienteDTO)
			throws ClienteException, CampoObrigatorioException {
		logger.info("Rest incluir cliente");

		ClienteDTO responseClienteDTO = clienteService.incluir(clienteDTO);
		return new ResponseEntity<ClienteDTO>(responseClienteDTO, HttpStatus.OK);
	}

	@PutMapping("/cliente/{id}")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable(value = "id") String id,
			@RequestBody @Valid ClienteDTO clienteDTODetails) {
		logger.info("Rest atualizar cliente");

		ClienteDTO clienteDTO;
		clienteDTO = clienteService.atualizar(id, clienteDTODetails);

		return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
	}

	@PutMapping("/cliente/cpf/{cpf}")
	public ResponseEntity<ClienteDTO> atualizarPorCpf(@PathVariable(value = "cpf") String cpf,
			@RequestBody @Valid ClienteDTO clienteDTODetails) {
		logger.info("Rest atualizar cliente");

		ClienteDTO clienteDTO;
		clienteDTO = clienteService.atualizarPorCpf(cpf, clienteDTODetails);

		return new ResponseEntity<ClienteDTO>(clienteDTO, HttpStatus.OK);
	}

	@DeleteMapping("/cliente/{id}")
	public ResponseEntity<ClienteDTO> deletar(@PathVariable(value = "id") String id)
			throws ClienteException, CampoObrigatorioException {
		logger.info("Rest deletar cliente");
		try {
			clienteService.deletar(id);
		} catch (ClienteNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
		
		return new ResponseEntity<ClienteDTO>(HttpStatus.OK);
	}

	@DeleteMapping("/cliente/cpf/{cpf}")
	public ResponseEntity<ClienteDTO> deletarPorCpf(@PathVariable(value = "cpf") String cpf)
			throws ClienteException, CampoObrigatorioException {
		logger.info("Rest deletar cliente");

		try {
			clienteService.deletarPorCpf(cpf);
		} catch (ClienteNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
		return new ResponseEntity<ClienteDTO>(HttpStatus.OK);
	}

	@PostMapping("/clientes/consultar/reclamantes")
	public ConsultaResponse buscarClientesQueReclamam(
			@RequestBody @Valid ConsultaReclamacaoRequestDTO consultaReclamacaoRequestDTO) {
		return clienteService.buscarClientesQueReclamam(consultaReclamacaoRequestDTO);
	}

}