package com.cursorestapi.apirest.resources;

import java.util.Collections;
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
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value = "/ranking", method = RequestMethod.GET)
	public ResponseEntity<?> rank() {
		List<Disciplina> list = disciplinaService.findAll();
		Collections.sort(list);
		return ResponseEntity.ok().body(list);
	}
		
	@RequestMapping(value = "/{id}/nome", method = RequestMethod.PUT)
	public ResponseEntity<?> updateName(@PathVariable Integer id, @RequestBody Disciplina obj) {
		Disciplina objOld = disciplinaService.findById(id);
		objOld.setNome(obj.getNome());
		return ResponseEntity.ok().body(objOld);
	}
	
	@RequestMapping(value = "/{id}/nota", method = RequestMethod.PUT)
	public ResponseEntity<?> updateNota(@PathVariable Integer id, @RequestBody Disciplina obj) {
		Disciplina objOld = disciplinaService.findById(id);
		objOld.setNota(obj.getNota());
		return ResponseEntity.ok().body(objOld);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		disciplinaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
