package com.blog.ggr.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.ggr.model.categoria;

@Repository

public interface repositoriotema extends JpaRepository<categoria, Long> {
	
	public List<categoria> findAllByTemaContainingIgnoreCase(String tema);

}
