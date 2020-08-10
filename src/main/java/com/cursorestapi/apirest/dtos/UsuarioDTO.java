package com.cursorestapi.apirest.dtos;

import java.io.Serializable;

import com.cursorestapi.apirest.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String primeiro_nome;
	private String ultimo_nome;
	private String senha;
	
	public UsuarioDTO() {
	}
	
	public UsuarioDTO(Usuario user) {
		this.email = user.getEmail();
		this.primeiro_nome = user.getPrimeiro_nome();
		this.ultimo_nome = user.getUltimo_nome();
		this.senha = user.getSenha();
	}
}
