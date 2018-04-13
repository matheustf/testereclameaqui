package br.com.reclameaqui.teste.service;

import java.util.List;

import br.com.reclameaqui.teste.dtos.ClienteDTO;
import br.com.reclameaqui.teste.exceptions.CampoNaoEncontradoException;
import br.com.reclameaqui.teste.exceptions.CampoObrigatorioException;
import br.com.reclameaqui.teste.exceptions.ClienteException;
import br.com.reclameaqui.teste.exceptions.ClienteNaoEncontradoException;

public interface ClienteService {
	
	ClienteDTO incluir(ClienteDTO blocoDTO) throws CampoObrigatorioException, ClienteException;
	
	ClienteDTO atualizar(String id, ClienteDTO blocoDTODetails) throws ClienteNaoEncontradoException;
	
	List<ClienteDTO> buscarTodos();

	void deletar(String id) throws ClienteNaoEncontradoException;

	ClienteDTO consultar(String id) throws ClienteNaoEncontradoException;

}
