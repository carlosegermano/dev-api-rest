package com.cursorestapi.apirest.dtos;

import java.io.Serializable;

import com.cursorestapi.apirest.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String nome;
	private String senha;
	
	public UsuarioDTO() {
	}
	
	public UsuarioDTO(Usuario user) {
		this.email = user.getEmail();
		this.nome = user.getNome();
		this.senha = user.getSenha();
	}
}
