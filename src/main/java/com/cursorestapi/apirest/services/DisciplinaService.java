package com.cursorestapi.apirest.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.dtos.DisciplinaDTO;
import com.cursorestapi.apirest.model.Disciplina;

@Service
public class DisciplinaService {

	@Autowired
	private RankService rankService;
	
	private List<Disciplina> disciplinas = new ArrayList<>();
	
	private int idAtual = 0;
	
	public void insert(Disciplina obj) {
		incrementa();
		obj.setId(idAtual);
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
	
	public List<Disciplina> rank() {
		return this.rankService.quickSort(disciplinas, 1, disciplinas.size() - 1);
	}
	
	private void incrementa() {
		this.idAtual++;
	}

	public Disciplina fromDTO(Integer id, DisciplinaDTO objDto) {
		Disciplina obj = new Disciplina(id, objDto.getNome(), objDto.getNota());
		return obj;
	}

	public Disciplina delete(Integer id) {
		Disciplina obj = findById(id);
		int index = this.disciplinas.indexOf(obj);
		return this.disciplinas.remove(index);
	}
	
}
