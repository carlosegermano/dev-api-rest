package com.cursorestapi.apirest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Disciplina implements Comparable<Disciplina>, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private double nota;
	private String comentarios = "";
	private int likes = -1;

	public Disciplina() {
	}

	public Disciplina(Long id, String nome, double nota, String comentarios, int likes) {
		super();
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.comentarios = comentarios;
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

	public String getComentarios() {
		return comentarios;
	}

	public void addComentarios(String comentarios) {
		if (this.comentarios.equals("")) {
			this.comentarios = comentarios;
		} else {
			this.comentarios += "\n" + comentarios;
		}
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
