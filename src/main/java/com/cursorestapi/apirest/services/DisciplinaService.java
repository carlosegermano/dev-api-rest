package com.cursorestapi.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.daos.DisciplinaRepository;
import com.cursorestapi.apirest.dtos.DisciplinaComentarioDTO;
import com.cursorestapi.apirest.dtos.DisciplinaLikesDTO;
import com.cursorestapi.apirest.dtos.DisciplinaNotaDTO;
import com.cursorestapi.apirest.model.Disciplina;

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

	public Optional<Disciplina> findById(Long id) {
		return disciplinaRepository.findById(id);
	}

	public void delete(Long id) {
		findById(id);
		disciplinaRepository.deleteById(id);
	}

	public Disciplina updateNome(Disciplina newObj, Disciplina obj) {
		newObj.setNome(obj.getNome());
		return disciplinaRepository.save(newObj);
	}

	public DisciplinaNotaDTO updateNota(Disciplina newObj, Disciplina obj) {
		if (newObj.getNota() == -1) {
			newObj.setNota(obj.getNota());
		} else {
			double nota1 = newObj.getNota();
			double nota2 = obj.getNota();
			double media = (nota1 + nota2) / 2;
			newObj.setNota(media);
		}
		
		disciplinaRepository.save(newObj);
		DisciplinaNotaDTO objDto = new DisciplinaNotaDTO(newObj);
		return objDto;
	}

	public DisciplinaLikesDTO increaseLikes(Disciplina obj) {
		obj.increaseLikes();
		disciplinaRepository.save(obj);
		return new DisciplinaLikesDTO(obj);
	}
	
	public DisciplinaComentarioDTO comment(Disciplina obj, Disciplina objJson) {
		obj.addComentarios(objJson.getComentarios());
		disciplinaRepository.save(obj);
		return new DisciplinaComentarioDTO(obj);
	}
	
	public List<Disciplina> sortByNota(List<Disciplina> list) {
		return disciplinaRepository.findByOrderByNotaDesc();
	}
	
	public List<Disciplina> sortByLikes(List<Disciplina> list) {
		return disciplinaRepository.findByOrderByLikesDesc();
	}
	
	/*
	public List<Disciplina> sortByNota(List<Disciplina> list) {
		Comparator<Disciplina> comp = new Comparator<Disciplina>() {
			public int compare(Disciplina d1, Disciplina d2) {
				if (d1.getNota() > d2.getNota()) {
					return -1;
				} else {
					return 1;
				}
			}
		};
		Collections.sort(list, comp);
		return list;
	}
	
	public List<Disciplina> sortByLikes(List<Disciplina> list) {
		Comparator<Disciplina> comp = new Comparator<Disciplina>() {
			public int compare(Disciplina d1, Disciplina d2) {
				if (d1.getLikes() > d2.getLikes()) {
					return -1;
				} else {
					return 1;
				}
			}
		};
		Collections.sort(list, comp);
		return list;
	}
	*/
}
