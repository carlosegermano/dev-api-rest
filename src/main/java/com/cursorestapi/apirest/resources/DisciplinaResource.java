package com.cursorestapi.apirest.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursorestapi.apirest.model.Disciplina;
import com.cursorestapi.apirest.services.DisciplinaService;

@RestController
@RequestMapping(value = "v1/api/disciplinas")
public class DisciplinaResource {
	
	@Autowired
	private DisciplinaService disciplinaService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Disciplina> insert(@RequestBody Disciplina obj) {
		disciplinaService.insert(obj);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Disciplina>> findAll() {
		return ResponseEntity.ok().body(disciplinaService.findAll());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Disciplina obj = disciplinaService.findById(id);
		if(obj == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/ranking", method = RequestMethod.GET)
	public ResponseEntity<List<Disciplina>> rank() {
		List<Disciplina> obj = disciplinaService.rank();
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		Disciplina obj = disciplinaService.delete(id);
		return ResponseEntity.ok().body(obj);
	}

}
