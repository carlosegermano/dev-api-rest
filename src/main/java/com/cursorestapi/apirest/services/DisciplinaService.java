package com.cursorestapi.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.daos.DisciplinaRepository;
import com.cursorestapi.apirest.model.Disciplina;
import com.cursorestapi.apirest.services.exceptions.ObjectNotFoundException;

@Service
public class DisciplinaService {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
		
	public void insert(Disciplina obj) {
		disciplinaRepository.save(obj);
	}

	public List<Disciplina> findAll() {
		return disciplinaRepository.findAll();
	}
	
	public Disciplina findById(Integer id) {
		Optional<Disciplina> obj = disciplinaRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Disciplina.class.getName()));
	}
	
	public void delete(Integer id) {
		findById(id);
		disciplinaRepository.deleteById(id);
	}
	
}
