package com.blog.ggr.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.ggr.model.modeltema;

@Repository

public interface repositoriotema extends JpaRepository<modeltema, Long> {
	
	public List<modeltema> findAllByTemaContainingIgnoreCase(String tema);

}
