package com.cursorestapi.apirest.dtos;

import java.io.Serializable;

import lombok.Data;

@Data
public class UsuarioLoginDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;
}
