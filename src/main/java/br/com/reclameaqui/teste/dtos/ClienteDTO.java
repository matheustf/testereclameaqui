package br.com.reclameaqui.teste.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;

import br.com.reclameaqui.teste.documents.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

	public ClienteDTO(String nome, String telefone, String cpf, EnderecoDTO endereco) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.endereco = endereco;
	}
	
	/*
	public ClienteDTO asEntity() {
        return ClienteDTO.builder()
                .id(id)
                .nickname(nickname)
                .name(name)
                .surname(surname)
                .rankPoints(rankPoints)
                .build();
    }
*/
}