package com.cursorestapi.apirest.dtos;

import com.cursorestapi.apirest.model.Disciplina;

public class DisciplinaComentarioDTO {

	private long id;
	private String nome;
	private String comentarios;
	
	public DisciplinaComentarioDTO() {
	}
	
	public DisciplinaComentarioDTO(Disciplina obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.comentarios = obj.getComentarios();
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

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

}
