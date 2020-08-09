package com.cursorestapi.apirest.services;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.daos.DisciplinaRepository;
import com.cursorestapi.apirest.daos.UsuarioRepository;
import com.cursorestapi.apirest.model.Disciplina;
import com.cursorestapi.apirest.model.Usuario;
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
		
		Usuario user1 = new Usuario("carlosegermano@gmail.com", "Carlos Eduardo", encoder.encode("123"), 1);
		
		usuarioRepository.save(user1);
		
		try {

			FileReader file = new FileReader("disciplinas.json");
			Object obj = JsonParser.parseReader(file);
			JsonArray disciplinas = (JsonArray) obj;
			Gson gson = new Gson();
			disciplinas.forEach(disc -> disciplinaRepository.save(gson.fromJson(disc, Disciplina.class)));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
