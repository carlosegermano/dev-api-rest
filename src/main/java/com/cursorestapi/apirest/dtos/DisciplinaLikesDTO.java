package com.cursorestapi.apirest.dtos;

import com.cursorestapi.apirest.model.Disciplina;

public class DisciplinaLikesDTO {

	private long id;
	private String nome;
	private int likes;
	
	public DisciplinaLikesDTO() {
	}
	
	public DisciplinaLikesDTO(Disciplina obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.likes = obj.getLikes();
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
	public int getLikes() {
		return likes;
	}

}
