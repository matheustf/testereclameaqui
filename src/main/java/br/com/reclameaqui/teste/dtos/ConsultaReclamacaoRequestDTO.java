package br.com.reclameaqui.teste.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaReclamacaoRequestDTO {

	private String empresa;
	
	private String cidade;
	
}