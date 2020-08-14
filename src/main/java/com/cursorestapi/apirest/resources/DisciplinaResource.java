package com.cursorestapi.apirest.resources;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursorestapi.apirest.dtos.DisciplinaComentarioDTO;
import com.cursorestapi.apirest.dtos.DisciplinaDTO;
import com.cursorestapi.apirest.dtos.DisciplinaNotaDTO;
import com.cursorestapi.apirest.dtos.PerfilDisciplina;
import com.cursorestapi.apirest.model.Comentario;
import com.cursorestapi.apirest.model.Disciplina;
import com.cursorestapi.apirest.services.DisciplinaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("/Disciplina")
@RestController
@RequestMapping(value = "v1/api/disciplinas")
public class DisciplinaResource {
	
	@Autowired
	private DisciplinaService disciplinaService;

	@ApiOperation(value = "Inserir disciplina")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Criado", response = Disciplina.class) })
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Disciplina> insert(@RequestBody Disciplina obj) {
		disciplinaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value = "Inserir comentário")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Criado", response = DisciplinaComentarioDTO.class) })
	@RequestMapping(value = "/comentarios/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> comment(@PathVariable Long id, @RequestBody Comentario comentario) {
		Optional<Disciplina> newObj = disciplinaService.findById(id);
		if (newObj.isPresent()) {
			DisciplinaComentarioDTO objUpdated = disciplinaService.comment(newObj.get(), comentario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
					.path("/{id}").buildAndExpand(objUpdated.getId()).toUri();
			return ResponseEntity.created(uri).build();
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Dar LIKE em uma disciplina")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Nenhum conteúdo") })
	@RequestMapping(value = "/givelike/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> updateLike(@PathVariable Long id) {
		Optional<Disciplina> obj = disciplinaService.findById(id);
		disciplinaService.updateLikes(obj.get());
		return ResponseEntity.noContent().build();
	}
	
	@ApiOperation(value = "Buscar todas as disciplinas")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = DisciplinaDTO.class) })
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DisciplinaDTO>> findAll() {
		List<Disciplina> list = disciplinaService.findAll();
		List<DisciplinaDTO> listDto = list.stream().map(
				obj -> new DisciplinaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value = "Buscar todas as disciplina contendo um termo específico")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = DisciplinaDTO.class) })
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ResponseEntity<List<DisciplinaDTO>> findAllByNomeContains(@RequestParam(value = "nome") String nome) {
		List<Disciplina> list = disciplinaService.findAllByNomeContains(nome);
		List<DisciplinaDTO> listDto = list.stream().map(
				obj -> new DisciplinaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	private ResponseEntity<?> find(@PathVariable Long id) {
		Optional<Disciplina> obj = disciplinaService.findById(id);
		return (obj.isPresent()) ? ResponseEntity.ok().body(obj) :
			new ResponseEntity<Disciplina>(HttpStatus.NOT_FOUND);
	}

	@ApiOperation(value = "Buscar perfil da disciplina por ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = PerfilDisciplina.class) })
	@RequestMapping(value = "/perfil/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> obterPerfil(@PathVariable Long id) {
		PerfilDisciplina perfil = disciplinaService.obterPerfilDisciplina(id);
		return ResponseEntity.ok().body(perfil);
	}
	
	@ApiOperation(value = "Rankear disciplinas por nota")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Disciplina.class) })
	@RequestMapping(value = "/ranking/notas", method = RequestMethod.GET)
	public ResponseEntity<?> rankByNotas() {
		List<Disciplina> list = disciplinaService.findAll();
		List<Disciplina> sortedList = disciplinaService.sortByNota(list);
		
		return ResponseEntity.ok().body(sortedList);
	}
	
	@ApiOperation(value = "Rankear disciplinas por número de likes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Disciplina.class) })
	@RequestMapping(value = "/ranking/likes", method = RequestMethod.GET)
	public ResponseEntity<?> rankByLikes() {
		List<Disciplina> list = disciplinaService.findAll();
		List<Disciplina> sortedList = disciplinaService.sortByLikes(list);
		return ResponseEntity.ok().body(sortedList);
	}
		
	@ApiOperation(value = "Atualizar nome da disciplina")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = Disciplina.class),
			@ApiResponse(code = 404, message = "Disciplina não encontrada")})
	@RequestMapping(value = "/{id}/nome", method = RequestMethod.PUT)
	public ResponseEntity<?> updateName(@PathVariable Long id, @RequestBody Disciplina obj) {
		Optional<Disciplina> newObj = disciplinaService.findById(id);
		if (newObj.isPresent()) {
			Disciplina objUpdated = disciplinaService.updateName(newObj.get(), obj);
			return ResponseEntity.ok().body(objUpdated);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Dar nota e atualiza média geral")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "OK", response = Disciplina.class),
			@ApiResponse(code = 404, message = "Disciplina não encontrada")})
	@RequestMapping(value = "/{id}/nota", method = RequestMethod.PUT)
	public ResponseEntity<?> updateNota(@PathVariable Long id, @RequestBody Disciplina obj) {
		Optional<Disciplina> newObj = disciplinaService.findById(id);
		if (newObj.isPresent()) {
			DisciplinaNotaDTO objUpdated = disciplinaService.updateNota(newObj.get(), obj);
			return ResponseEntity.ok().body(objUpdated);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Apagar disciplina por ID")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Nenhum conteúdo") })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		disciplinaService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Inativar comentário")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Nenhum conteúdo") })
	@RequestMapping(value = "/{disciplina_id}/{comentario_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteComment(@PathVariable Long disciplina_id, @PathVariable Long comentario_id) {
		disciplinaService.deleteComment(disciplina_id, comentario_id);
		return ResponseEntity.noContent().build();
	}

}
