package com.cursorestapi.apirest.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.cursorestapi.apirest.daos.ComentarioRepository;
import com.cursorestapi.apirest.daos.DisciplinaRepository;
import com.cursorestapi.apirest.daos.UsuarioRepository;
import com.cursorestapi.apirest.dtos.DisciplinaComentarioDTO;
import com.cursorestapi.apirest.dtos.DisciplinaLikesDTO;
import com.cursorestapi.apirest.dtos.DisciplinaNotaDTO;
import com.cursorestapi.apirest.model.Comentario;
import com.cursorestapi.apirest.model.Disciplina;
import com.cursorestapi.apirest.model.Usuario;
import com.cursorestapi.apirest.security.UserSpringSecurity;

@Service
public class DisciplinaService {

	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;
	
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
	
	public void deleteComment(Long disciplina_id, Long comentario_id) {
		Optional<Disciplina> disc = findById(disciplina_id);
		List<Comentario> comentarios = disc.get().getComentarios();
		for (Comentario comentario : comentarios) {
			if (comentario.getId() == comentario_id) {
				comentario.setAtivo(false);
				break;
			}	
		}
	}

	public Disciplina updateName(Disciplina newObj, Disciplina obj) {
		newObj.setNome(obj.getNome());
		return disciplinaRepository.save(newObj);
	}

	public DisciplinaNotaDTO updateNota(Disciplina newObj, Disciplina obj) {
		
		if (newObj.getNota() == -1) {
			newObj.setNota(obj.getNota());
			newObj.addNotas(obj.getNota());
		} else {
			double nota1 = newObj.getNota();
			double nota2 = obj.getNota();
			double media = (nota1 + nota2) / 2;
			newObj.setNota(media);
			newObj.addNotas(obj.getNota());
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
	
	public DisciplinaComentarioDTO comment(Disciplina obj, Comentario comentario) {
		
		UserSpringSecurity uss = (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Usuario> user = usuarioRepository.findByEmail(uss.getUsername());
		
		comentario.setInstante(new Date());
		comentario.setDisciplina(obj);
		comentario.setUsuario(user.get());
		
		comentarioRepository.save(comentario);
		
		user.get().addComentario(comentario);
		
		usuarioRepository.save(user.get());
		
		obj.addComentario(comentario);
		
		disciplinaRepository.save(obj);

		return new DisciplinaComentarioDTO(obj);
	}
	
	public List<Disciplina> sortByNota(List<Disciplina> list) {
		return disciplinaRepository.findByOrderByNotaDesc();
	}
	
	public List<Disciplina> sortByLikes(List<Disciplina> list) {
		return disciplinaRepository.findByOrderByLikesDesc();
	}

	public List<Disciplina> findAllByNomeContains(String nome) {
		List<Disciplina> list = disciplinaRepository.findAllByNomeContains(nome);
		return list;
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
