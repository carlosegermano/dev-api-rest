package com.cursorestapi.apirest.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cursorestapi.apirest.model.Comentario;
import com.cursorestapi.apirest.model.Disciplina;

import lombok.Data;

@Data
public class PerfilDisciplina {

	private long id_disciplina;
	private String nome_disciplina;
	private int likes;
	private int qtd_comentarios;
	private Set<Double> notas = new HashSet<>();
	private List<Comentario> comentarios = new ArrayList<>();
	private double nota;
	
	public PerfilDisciplina(Disciplina obj) {
		this.id_disciplina = obj.getId();
		this.nome_disciplina = obj.getNome();
		this.likes = obj.getLikes();
		this.qtd_comentarios = obj.getNumComentarios();
		this.notas = obj.getNotas();
		this.comentarios = updateComentarios(obj.getComentarios());
	}

	private List<Comentario> updateComentarios(List<Comentario> comentarios) {
		List<Comentario> list = new ArrayList<>();
		
		for (Comentario comentario : comentarios) {
			if (!comentario.isAtivo()) {
				comentario.setDescricao("");
			}
			list.add(comentario);
		}
		return list;
	}
}
