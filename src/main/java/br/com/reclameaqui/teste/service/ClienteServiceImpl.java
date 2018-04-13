package br.com.reclameaqui.teste.service;

import java.lang.reflect.Type;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;

import br.com.reclameaqui.teste.documents.Cliente;
import br.com.reclameaqui.teste.dtos.ClienteDTO;
import br.com.reclameaqui.teste.dtos.ConsultaReclamacaoRequestDTO;
import br.com.reclameaqui.teste.dtos.ConsultaResponse;
import br.com.reclameaqui.teste.enums.TipoConsulta;
import br.com.reclameaqui.teste.exceptions.CampoNaoEncontradoException;
import br.com.reclameaqui.teste.exceptions.CampoObrigatorioException;
import br.com.reclameaqui.teste.exceptions.ClienteException;
import br.com.reclameaqui.teste.repository.ClienteRepository;
import br.com.reclameaqui.teste.repository.ReclamacaoRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

	private final Logger logger = Logger.getLogger(ClienteServiceImpl.class);

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ReclamacaoRepository reclamacaoRepository;
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Override
	public ClienteDTO consultar(String id) throws CampoNaoEncontradoException {
		logger.info("Consultando cliente...");
		Cliente cliente = clienteRepository.findById(id);
		if (cliente == null) {
			logger.error("Erro ao consultar Cliente!");
			throw new CampoNaoEncontradoException("Cliente Nao encontrado!");
		}

		ClienteDTO clienteDTO = modelMapper().map(cliente, ClienteDTO.class);
		
		return clienteDTO;
	}

	@Override
	public List<ClienteDTO> buscarTodos() {
		logger.info("Consultando cliente...");

		List<Cliente> clientes = (List<Cliente>) clienteRepository.findAll();

		Type listType = new TypeToken<List<ClienteDTO>>() {}.getType();
		List<ClienteDTO> clientesDTO = modelMapper().map(clientes, listType);

		return clientesDTO;
	}

	@Override
	public ClienteDTO incluir(ClienteDTO clienteDTO) throws ClienteException {
		logger.info("Incluindo cliente...");

		//Reclamacao reclamacoes = new Reclamacao(clienteDTO.getReclamacoes(),clienteDTO.getReclamacoes());
		
		//Type listType = new TypeToken<List<R>>() {}.getType();
		//List<Reclamacao> reclamacoes = modelMapper().map(clienteDTO.getReclamacoes(), listType);
		
		Cliente cliente = modelMapper().map(clienteDTO, Cliente.class);
		//cliente.set

		clienteRepository.save(cliente);
		
		return modelMapper().map(cliente, ClienteDTO.class);
	}

	@Override
	public ClienteDTO atualizar(String id, ClienteDTO clienteDTODetails) throws CampoNaoEncontradoException {
		logger.info("Atualizando cliente...");

		Cliente cliente = clienteRepository.findOne(id);
		Cliente clienteDetails = modelMapper().map(clienteDTODetails, Cliente.class);
		
		cliente = cliente.update(cliente, clienteDetails);

		clienteRepository.save(cliente);
		
		return modelMapper().map(cliente, ClienteDTO.class);
	}
	
	@Override
	public void deletar(String id) throws CampoObrigatorioException {
		logger.info("Deletando cliente... id: " + id);
		Cliente cliente = clienteRepository.findOne(id);
		if (cliente == null) {
			logger.error("Erro ao deletar Cliente!");
			throw new CampoObrigatorioException("Cliente Nao encontrado para ser excluido!");
		}
		clienteRepository.delete(id);
	}
	
	public ConsultaResponse buscarClientesQueReclamam(ConsultaReclamacaoRequestDTO consulta) {
		
		TipoConsulta tipocConsulta = verificaParametros(consulta);
		
		List<Cliente> clientes = reclamacaoRepository.buscarClientesQueReclamam(tipocConsulta,consulta);
		
		Type listType = new TypeToken<List<ClienteDTO>>() {}.getType();
		List<ClienteDTO> clientesDTO = modelMapper().map(clientes, listType);
		
		return new ConsultaResponse(clientes.size(),clientesDTO);
	}

	private TipoConsulta verificaParametros(ConsultaReclamacaoRequestDTO consulta) {
		if(StringUtils.isNotBlank(consulta.getEmpresa()) && StringUtils.isNotBlank(consulta.getCidade())) {
			return TipoConsulta.EMPRESAECIDADE;
		}
		
		if(StringUtils.isNotBlank(consulta.getCidade())) {
			return TipoConsulta.CIDADE;
		}
		
		if(StringUtils.isNotBlank(consulta.getEmpresa())) {
			return TipoConsulta.EMPRESA;
		}
		return null;
	}

	public ClienteDTO consultarPorCpf(String cpf) throws CampoNaoEncontradoException {
		logger.info("Consultando cliente...");
		Cliente cliente = clienteRepository.findByCpf(cpf);
		if (cliente == null) {
			logger.error("Erro ao consultar Cliente!");
			throw new CampoNaoEncontradoException("Cliente Nao encontrado!");
		}

		ClienteDTO clienteDTO = modelMapper().map(cliente, ClienteDTO.class);
		
		return clienteDTO;
	}

	public void deletarPorCpf(String cpf) throws CampoObrigatorioException {
		logger.info("Deletando cliente... cpf: " + cpf);
		Cliente cliente = clienteRepository.findByCpf(cpf);
		if (cliente == null) {
			logger.error("Erro ao deletar Cliente!");
			throw new CampoObrigatorioException("Cliente Nao encontrado para ser excluido!");
		}
		clienteRepository.delete(cliente.getId());
		
	}

	public ClienteDTO atualizarPorCpf(String cpf, ClienteDTO clienteDTODetails) {
		logger.info("Atualizando cliente...");

		Cliente cliente = clienteRepository.findByCpf(cpf);
		Cliente clienteDetails = modelMapper().map(clienteDTODetails, Cliente.class);
		
		cliente = cliente.update(cliente, clienteDetails);

		clienteRepository.save(cliente);
		
		return modelMapper().map(cliente, ClienteDTO.class);
	}
	

}
