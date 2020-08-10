package com.cursorestapi.apirest.dtos;

import java.util.ArrayList;
import java.util.List;

import com.cursorestapi.apirest.model.Comentario;
import com.cursorestapi.apirest.model.Disciplina;

public class DisciplinaComentarioDTO {

	private long id;
	private String nome;
	private List<Comentario> comentarios = new ArrayList<>();
	
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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void addComentarios(Comentario comentario) {
		this.comentarios.add(comentario);
	}

}
