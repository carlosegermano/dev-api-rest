package com.cursorestapi.apirest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursorestapi.apirest.model.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	
	List<Disciplina> findByOrderByNotaDesc();
	
	List<Disciplina> findByOrderByNumLikesDesc();
	
	List<Disciplina> findAllByNomeContains(String nome);
}
