package com.blog.ggr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.ggr.model.Tema;
import com.blog.ggr.repositorio.repositoriotema;

@RestController
@CrossOrigin(origins= "*", allowedHeaders = "*")
@RequestMapping("/tema")


public class controllertema {
	
	@Autowired
	private repositoriotema rt;
	
	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(rt.findAll());
		
	}
	
	@GetMapping("/{id_tema}")
	public ResponseEntity<Tema> getById(@PathVariable long id_tema){
		return rt.findById(id_tema).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByname (@PathVariable String nome)
	{
		return ResponseEntity.ok(rt.findAllByTemaContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Tema> post (@RequestBody Tema tema)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(rt.save(tema));
	}
	
	@PutMapping
	public ResponseEntity<Tema> put (@RequestBody Tema tema)
	{
		return ResponseEntity.ok(rt.save(tema));
	}
	
	@DeleteMapping("/{id_tema}")
	public void delete (@PathVariable long id_tema) {
		rt.deleteById(id_tema);
	}

}
