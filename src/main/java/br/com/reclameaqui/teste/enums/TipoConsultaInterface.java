package br.com.reclameaqui.teste.enums;

import org.springframework.data.mongodb.core.aggregation.MatchOperation;

import br.com.reclameaqui.teste.dtos.ConsultaReclamacaoRequestDTO;

public interface TipoConsultaInterface {
	
	 public MatchOperation matchOperation(ConsultaReclamacaoRequestDTO request);

}
