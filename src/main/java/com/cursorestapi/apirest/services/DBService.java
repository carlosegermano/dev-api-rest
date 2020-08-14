package com.cursorestapi.apirest.services;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.model.Disciplina;
import com.cursorestapi.apirest.model.Usuario;
import com.cursorestapi.apirest.model.enums.Perfil;
import com.cursorestapi.apirest.repository.DisciplinaRepository;
import com.cursorestapi.apirest.repository.UsuarioRepository;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@Service
public class DBService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	public void instantiateTestDatabase() throws ParseException {
		
		Usuario user1 = new Usuario("carlosegermano@gmail.com", "Carlos", "Germano", encoder.encode("123"));
		Usuario user2 = new Usuario("ana@gmail.com", "Ana", "Dantas", encoder.encode("123"));

		user1.addPerfil(Perfil.ADMIN);
		usuarioRepository.saveAll(Arrays.asList(user1, user2));
		
		JsonArray disciplinas = null;
		Gson gson = new Gson();
		
		try {

			FileReader file = new FileReader("disciplinas.json");
			Object obj = JsonParser.parseReader(file);
			disciplinas = (JsonArray) obj;
			disciplinas.forEach(
					disc -> disciplinaRepository.save(
							gson.fromJson(
									disc, Disciplina.class)));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
}
