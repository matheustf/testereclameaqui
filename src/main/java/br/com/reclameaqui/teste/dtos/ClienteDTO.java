package br.com.reclameaqui.teste.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;

import br.com.reclameaqui.teste.documents.Endereco;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO {

	@Id
	private String id;

	@NotNull()
	private String nome;
	
	private String telefone;
	
	@CPF
	@NotNull()
	private String cpf;
	
	private EnderecoDTO endereco;
	

}