package br.com.reclameaqui.teste.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReclamantesRequestDTO {

	private String empresa;
	
	private String cidade;
	
}