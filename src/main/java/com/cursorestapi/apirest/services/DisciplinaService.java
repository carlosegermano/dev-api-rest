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
import com.cursorestapi.apirest.dtos.PerfilDisciplina;
import com.cursorestapi.apirest.model.Comentario;
import com.cursorestapi.apirest.model.Disciplina;
import com.cursorestapi.apirest.model.Usuario;
import com.cursorestapi.apirest.model.enums.Perfil;
import com.cursorestapi.apirest.security.UserSpringSecurity;
import com.cursorestapi.apirest.services.exceptions.AuthorizationException;
import com.cursorestapi.apirest.services.exceptions.CommentActivityException;
import com.cursorestapi.apirest.services.exceptions.CommentNotFoundException;

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

	public PerfilDisciplina obterPerfilDisciplina(Long id) {
		Optional<Disciplina> obj = findById(id);
		return new PerfilDisciplina(obj.get());
	}
	
	public Optional<Disciplina> findById(Long id) {
		return disciplinaRepository.findById(id);
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

		Optional<Usuario> user = getLoggedUser();

		obj.setNumComentarios(obj.getNumComentarios() + 1);

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

	public void delete(Long id) {
		findById(id);
		disciplinaRepository.deleteById(id);
	}

	public void deleteComment(Long disciplina_id, Long comentario_id) {

		Optional<Usuario> user = getLoggedUser();
		Optional<Disciplina> obj = findById(disciplina_id);
		Comentario comment = getComment(obj, comentario_id);

		if (permission(comment, user) && getActivity(comment)) {

			obj.get().setNumComentarios(obj.get().getNumComentarios() - 1);
			comment.setAtivo(false);
			
			Optional<Comentario> com = comentarioRepository.findById(comentario_id);
			com.get().setAtivo(false);
			
			comentarioRepository.save(comment);
		}
	}

	private boolean getActivity(Comentario comment) {
		if (!comment.isAtivo()) {
			throw new CommentActivityException("Comentário já está inativado!");
		}
		return true;
	}

	private Comentario getComment(Optional<Disciplina> obj, Long comentario_id) {
		Comentario comment = null;

		for (Comentario c : obj.get().getComentarios()) {
			if (c.getId() == comentario_id) {
				comment = c;
			}
		}

		if (comment == null) {
			throw new CommentNotFoundException("Comentário não encontrado!");
		}

		return comment;
	}

	private Optional<Usuario> getLoggedUser() {
		UserSpringSecurity uss = (UserSpringSecurity) SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getPrincipal();
		return usuarioRepository.findByEmail(uss.getUsername());
	}

	private boolean permission(Comentario comment, Optional<Usuario> user) {
		if (user.get().getEmail().equals(comment.getUsuario().getEmail())
				| user.get().getPerfis().contains(Perfil.ADMIN)) {
			return true;
		}
		throw new AuthorizationException(
				"Somente usuário com perfil de admin ou o autor do comentário pode excluí-lo!");
	}

}
