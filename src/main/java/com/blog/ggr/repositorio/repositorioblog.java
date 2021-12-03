package com.blog.ggr.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blog.ggr.model.modelblog;
@Repository
public interface repositorioblog extends JpaRepository<modelblog, Long>{

	List<modelblog> findAllByTituloContainingIgnoreCase(String titulo);

	
}
