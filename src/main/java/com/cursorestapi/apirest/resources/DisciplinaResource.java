package com.cursorestapi.apirest.resources;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursorestapi.apirest.dtos.DisciplinaComentarioDTO;
import com.cursorestapi.apirest.dtos.DisciplinaDTO;
import com.cursorestapi.apirest.dtos.DisciplinaLikesDTO;
import com.cursorestapi.apirest.dtos.DisciplinaNotaDTO;
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
	public ResponseEntity<List<DisciplinaDTO>> findAll() {
		List<Disciplina> list = disciplinaService.findAll();
		List<DisciplinaDTO> listDto = list.stream().map(
				obj -> new DisciplinaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		Optional<Disciplina> obj = disciplinaService.findById(id);
		return (obj.isPresent()) ? ResponseEntity.ok().body(obj) :
			new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/ranking/notas", method = RequestMethod.GET)
	public ResponseEntity<?> rankByNotas() {
		List<Disciplina> list = disciplinaService.findAll();
		Collections.sort(list);
		return ResponseEntity.ok().body(list);
	}
		
	@RequestMapping(value = "/{id}/nome", method = RequestMethod.PUT)
	public ResponseEntity<?> updateName(@PathVariable Long id, @RequestBody Disciplina obj) {
		Optional<Disciplina> newObj = disciplinaService.findById(id);
		if (newObj.isPresent()) {
			Disciplina objUpdated = disciplinaService.updateNome(newObj.get(), obj);
			return ResponseEntity.ok().body(objUpdated);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{id}/nota", method = RequestMethod.PUT)
	public ResponseEntity<?> updateNota(@PathVariable Long id, @RequestBody Disciplina obj) {
		Optional<Disciplina> newObj = disciplinaService.findById(id);
		if (newObj.isPresent()) {
			DisciplinaNotaDTO objUpdated = disciplinaService.updateNota(newObj.get(), obj);
			return ResponseEntity.ok().body(objUpdated);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/likes/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> increaseLikes(@PathVariable Long id) {
		Optional<Disciplina> newObj = disciplinaService.findById(id);
		if (newObj.isPresent()) {
			DisciplinaLikesDTO obj = disciplinaService.increaseLikes(newObj.get());
			return ResponseEntity.ok().body(obj);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/comentarios/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> comment(@PathVariable Long id, @RequestBody Disciplina obj) {
		Optional<Disciplina> newObj = disciplinaService.findById(id);
		if (newObj.isPresent()) {
			DisciplinaComentarioDTO objUpdated = disciplinaService.comment(newObj.get(), obj);
			return ResponseEntity.ok().body(objUpdated);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		disciplinaService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	
}
