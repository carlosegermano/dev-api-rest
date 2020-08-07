package com.cursorestapi.apirest.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursorestapi.apirest.dtos.UsuarioDTO;
import com.cursorestapi.apirest.model.Usuario;
import com.cursorestapi.apirest.services.UsuarioService;

@RestController
@RequestMapping(value = "v1/api/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
		
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> insert(@RequestBody UsuarioDTO userDto) {
		Usuario user = fromDTO(userDto);
		usuarioService.insert(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
		return ResponseEntity.created(uri).build();
	}

	private Usuario fromDTO(UsuarioDTO userDto) {
		return new Usuario(userDto.getEmail(), userDto.getNome(), encoder.encode(userDto.getSenha()));
	}
}
