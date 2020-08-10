package com.cursorestapi.apirest.dtos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.cursorestapi.apirest.model.Disciplina;

import lombok.Data;

@Data
public class PerfilDisciplina {

	private long id_disciplina;
	private String nome_disciplina;
	private int likes;
	private int qtd_comentarios;
	private Set<Double> notas = new HashSet<>();
	private List<String> comentarios = new ArrayList<>();
	private double nota;
	
	public PerfilDisciplina(Disciplina obj) {
		this.id_disciplina = obj.getId();
		this.nome_disciplina = obj.getNome();
		this.likes = obj.getLikes();
		this.qtd_comentarios = obj.getComentarios().size();
		this.notas = obj.getNotas();
		obj.getComentarios()
				.stream()
				.map(x -> this.comentarios.add(x.getDescricao()))
				.collect(Collectors.toList());
	}
}
