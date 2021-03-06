package br.com.reclameaqui.teste.repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.fields;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.core.aggregation.Aggregation;

import br.com.reclameaqui.teste.documents.Cliente;
import br.com.reclameaqui.teste.documents.Reclamacao;
import br.com.reclameaqui.teste.dtos.ReclamantesRequestDTO;
import br.com.reclameaqui.teste.enums.TipoConsulta;

public class ReclamacaoRepositoryImpl implements ReclamacaoRepositoryCustom{
	 
	 private final MongoTemplate mongoTemplate;

	    @Autowired
	    public ReclamacaoRepositoryImpl(MongoTemplate mongoTemplate) {
	        this.mongoTemplate = mongoTemplate;
	    }
	    
	    @Override
	    public List<Cliente> buscarClientesQueReclamam(TipoConsulta tipoConsulta, ReclamantesRequestDTO request) {
	        
	        
	        Aggregation newAggregation = Aggregation.newAggregation(tipoConsulta.matchOperation(request),
	                group(fields().and("idCliente", "idCliente"))
	                .first("idCliente").as("idCliente"),project().andExclude("_id")
	                );
	        
			List<Cliente> singleResults = mongoTemplate.aggregate(newAggregation, Reclamacao.class, Reclamacao.class).getMappedResults().stream().map(item -> item.getIdCliente()).collect(Collectors.toList());
			
			
			for (Cliente teste : singleResults) {
				System.out.println(teste);
			}
			
			return singleResults;
	    }
	    
}
