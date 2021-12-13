package com.blog.ggr.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.blog.ggr.model.produto;
@Repository
public interface repositorioblog extends JpaRepository<produto, Long>{

	List<produto> findAllByTituloContainingIgnoreCase(String titulo);

	
}
