package com.blog.ggr.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.ggr.model.Tema;

@Repository

public interface repositoriotema extends JpaRepository<Tema, Long> {
	
	public List<Tema> findAllByTemaContainingIgnoreCase(String tema);

}
