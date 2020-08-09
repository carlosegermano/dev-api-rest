package com.cursorestapi.apirest.daos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursorestapi.apirest.model.Disciplina;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
	
	List<Disciplina> findByOrderByNotaDesc();
	
	List<Disciplina> findByOrderByLikesDesc();
	
	List<Disciplina> findAllByNomeContains(String nome);
}
