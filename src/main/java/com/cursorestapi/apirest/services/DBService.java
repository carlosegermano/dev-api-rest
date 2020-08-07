package com.cursorestapi.apirest.services;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.daos.UsuarioRepository;
import com.cursorestapi.apirest.model.Usuario;

@Service
public class DBService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public void instantiateTestDatabase() throws ParseException {
		
		Usuario user1 = new Usuario("carlosegermano@gmail.com", "Carlos Eduardo", encoder.encode("123"));
		
		usuarioRepository.save(user1);
	}
}
