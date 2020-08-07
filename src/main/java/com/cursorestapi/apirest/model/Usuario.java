package com.cursorestapi.apirest.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"nome", "senha"})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String email;
	private String nome;
	private String senha;
	
	public boolean isValid() {
		System.out.println(!email.isEmpty());
		return !email.isEmpty() && !nome.isEmpty() && !senha.isEmpty();
	}
}
