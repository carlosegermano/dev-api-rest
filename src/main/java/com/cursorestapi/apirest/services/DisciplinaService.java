package com.cursorestapi.apirest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.model.Disciplina;

@Service
public class DisciplinaService {
	
	private List<Disciplina> disciplinas = new ArrayList<>();
	private int idAtual = 1;
	
	public void insert(Disciplina obj) {
		obj.setId(this.idAtual++);
		disciplinas.add(obj);
	}

	public List<Disciplina> findAll() {
		return disciplinas;
	}
	
	public Disciplina findById(Integer id) {
		for (Disciplina obj : disciplinas) {
			if (obj.getId() == id) return obj;
		}
		return null;
	}
	
	public Disciplina delete(Integer id) {
		Disciplina obj = findById(id);
		int index = this.disciplinas.indexOf(obj);
		return this.disciplinas.remove(index);
	}
	
}
