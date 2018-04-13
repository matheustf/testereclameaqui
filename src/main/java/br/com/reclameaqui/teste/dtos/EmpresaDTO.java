package br.com.reclameaqui.teste.dtos;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmpresaDTO {

	@NotNull()
	@CNPJ
	private String cnpj;

	@NotNull()
	private String nome;

}