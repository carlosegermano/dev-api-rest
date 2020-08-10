package com.cursorestapi.apirest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Disciplina implements Comparable<Disciplina>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private double nota;
	private int likes = -1;

	@OneToMany(mappedBy = "disciplina", cascade = CascadeType.ALL)
	private List<Comentario> comentarios = new ArrayList<>();
	
	public Disciplina() {
	}

	public Disciplina(Long id, String nome, double nota, int likes) {
		super();
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.likes = likes;
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

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void addComentario(Comentario comentario) {
		this.comentarios.add(comentario);
	}

	public int getLikes() {
		return likes;
	}

	public void increaseLikes() {
		this.likes = this.likes + 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public int compareTo(Disciplina obj) {
		if (obj.getNota() < this.nota) {
			return -1;
		} else {
			return 1;
		}
	}

}
