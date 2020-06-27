package com.cursorestapi.apirest.dtos;

public class DisciplinaDTO {

	private String nome;
	private double nota;
	
	public DisciplinaDTO(String nome, double nota) {
		super();
		this.nome = nome;
		this.nota = nota;
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
