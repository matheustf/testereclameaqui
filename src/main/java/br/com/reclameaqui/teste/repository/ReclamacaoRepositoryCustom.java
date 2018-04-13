package br.com.reclameaqui.teste.repository;

import java.util.List;

import br.com.reclameaqui.teste.documents.Cliente;
import br.com.reclameaqui.teste.dtos.ConsultaReclamacaoRequestDTO;
import br.com.reclameaqui.teste.enums.TipoConsulta;

public interface ReclamacaoRepositoryCustom {

	List<Cliente> buscarClientesQueReclamam(TipoConsulta tipocConsulta, ConsultaReclamacaoRequestDTO request);

}
