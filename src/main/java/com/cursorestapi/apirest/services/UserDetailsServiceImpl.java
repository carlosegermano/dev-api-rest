package com.cursorestapi.apirest.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.daos.UsuarioRepository;
import com.cursorestapi.apirest.model.Usuario;
import com.cursorestapi.apirest.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> user = usuarioRepository.findByEmail(email);
		if (user.isPresent()) {
			return new UserSpringSecurity(user.get().getEmail(), user.get().getSenha());
		}
		throw new UsernameNotFoundException(email);
	}

}
