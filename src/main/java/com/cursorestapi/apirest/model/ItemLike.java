package com.cursorestapi.apirest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class ItemLike implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "usuario_email")
	private Usuario usuario;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "disciplina_id")
	private Disciplina disciplina;
	
	private boolean gaveLike;
	
	public ItemLike() {
	}
	
	public ItemLike(Long id, Usuario usuario, Disciplina disciplina, boolean gaveLike) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.disciplina = disciplina;
		this.gaveLike = gaveLike;
	}
	
}
