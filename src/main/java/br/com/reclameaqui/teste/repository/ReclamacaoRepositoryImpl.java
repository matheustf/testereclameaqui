package br.com.reclameaqui.teste.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.fields;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;

import br.com.reclameaqui.teste.documents.Cliente;
import br.com.reclameaqui.teste.documents.Reclamacao;
import br.com.reclameaqui.teste.dtos.ReclamantesRequestDTO;
import br.com.reclameaqui.teste.enums.TipoConsulta;

public class ReclamacaoRepositoryImpl implements ReclamacaoRepositoryCustom{
	
	 private final MongoTemplate mongoTemplate;
	 
	 private final MongoOperations mongoOperations;
	 

	    @Autowired
	    public ReclamacaoRepositoryImpl(MongoTemplate mongoTemplate, MongoOperations mongoOperations) {
	        this.mongoTemplate = mongoTemplate;
	        this.mongoOperations = mongoOperations;
	    }
	    
	    @Override
	    public List<Cliente> buscarClientesQueReclamam(TipoConsulta tipoConsulta, ReclamantesRequestDTO request) {
	        
	        
	        Aggregation newAggregation = Aggregation.newAggregation(tipoConsulta.matchOperation(request),
	                group(fields().and("idCliente", "idCliente"))
	                .first("idCliente").as("idCliente"),project().andExclude("_id")
	                );
	        
			List<Cliente> singleResults = mongoOperations.aggregate(newAggregation, Reclamacao.class, Reclamacao.class).getMappedResults().stream().map(item -> item.getIdCliente()).collect(Collectors.toList());
			
			
			for (Cliente teste : singleResults) {
				System.out.println(teste);
			}
			
			return singleResults;
	    }

	    
	    /*
	         @Override
	    public List<Reclamacao> aggregate(ConsultaReclamacaoRequestDTO request) {
	        
	        
	        Aggregation newAggregation = Aggregation.newAggregation(match(where("endereco.cidade").is(request.getCidade()).and("empresa.nome").is(request.getEmpresa())),
	                group(fields().and("idCliente", "idCliente"))
	                .first("id").as("id")
	                .first("nome").as("nome")
	                .first("descricao").as("descricao")
	                .first("endereco").as("endereco")
	                .first("empresa").as("empresa")
	                .first("idCliente").as("idCliente"),project().andExclude("_id")
	                );
	        
//,project().andExclude("_id")
			List<Reclamacao> singleResults = mongoOperations.aggregate(newAggregation, Reclamacao.class, Reclamacao.class).getMappedResults().stream().map(item -> item).collect(Collectors.toList());
			
			
			
			for (Reclamacao teste : singleResults) {
				System.out.println(teste);
			}
			
			return singleResults;
	    }
	    
	     */



}
