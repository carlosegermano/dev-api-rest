package com.cursorestapi.apirest.daos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursorestapi.apirest.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	public Optional<Usuario> findByEmail(String email);
}
