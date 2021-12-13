package com.blog.ggr.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.ggr.model.Usuario;

public interface Usuariorepositorio extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByUsuario (String usuario);

}
