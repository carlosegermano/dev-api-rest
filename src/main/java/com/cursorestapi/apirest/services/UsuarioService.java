package com.cursorestapi.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.dtos.UsuarioDTO;
import com.cursorestapi.apirest.dtos.UsuarioLoginDTO;
import com.cursorestapi.apirest.model.Usuario;
import com.cursorestapi.apirest.repository.UsuarioRepository;
import com.cursorestapi.apirest.services.exceptions.DuplicatedUserException;
import com.cursorestapi.apirest.services.exceptions.InvalidUserException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public UsuarioDTO insert(Usuario user) {
		if (usuarioRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new DuplicatedUserException("Usuário já existente!");
		}
		if (!user.isValid()) {
			throw new InvalidUserException("Usuário inválido!");
		}
		this.usuarioRepository.save(user);
		return new UsuarioDTO(user);
	}

	public boolean validate(UsuarioLoginDTO userDTO) {
		Optional<Usuario> optUser = usuarioRepository.findByEmail(userDTO.getEmail());
		if (optUser.isPresent() && optUser.get().getSenha().equals(userDTO.getSenha())) {
			return true;
		}
		return false;
	}
}
