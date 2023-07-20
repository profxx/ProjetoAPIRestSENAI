package br.com.senai.dto;

import lombok.Getter;
import lombok.Setter;

public class ProfessorDTO {

	@Getter
	@Setter
	private Integer id;
	
	@Getter
	@Setter
	private String nome;
	
	@Getter
	@Setter
	private String sobrenome;
	
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
