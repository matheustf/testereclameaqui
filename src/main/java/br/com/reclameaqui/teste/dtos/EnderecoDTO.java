package br.com.reclameaqui.teste.dtos;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EnderecoDTO {
	
	@NotNull(message = "Campo Obrigatorio!")
	private String cep;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String logradouro;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String numero;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String bairro;
	
	@NotNull(message = "Campo Obrigatorio!")
	private String cidade;

}