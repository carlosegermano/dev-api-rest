package com.cursorestapi.apirest.dtos;

import com.cursorestapi.apirest.model.Disciplina;

public class DisciplinaNotaDTO {

	private long id;
	private String nome;
	private double nota;
	
	public DisciplinaNotaDTO() {
	}
	
	public DisciplinaNotaDTO(Disciplina obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.nota = obj.getNota();
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

	public double getNota() {
		return nota;
	}

	public void setNota(double nota) {
		this.nota = nota;
	}

}
