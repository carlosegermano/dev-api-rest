package com.cursorestapi.apirest.dtos;

import com.cursorestapi.apirest.model.Disciplina;

public class DisciplinaDTO {

	private long id;
	private String nome;
	
	public DisciplinaDTO() {
	}
	
	public DisciplinaDTO(Disciplina obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
