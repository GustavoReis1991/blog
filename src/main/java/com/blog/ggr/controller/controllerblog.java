package com.blog.ggr.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.blog.ggr.model.Postagem;
import com.blog.ggr.repositorio.repositorioblog;

@RestController
@RequestMapping("/blog.ggr")
@CrossOrigin(origins= "*", allowedHeaders="*")

public class controllerblog {
	
	@Autowired
	private repositorioblog rg;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(rg.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
		return rg.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo)
	{
		return ResponseEntity.ok (rg.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post (@Valid @RequestBody Postagem postagem)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(rg.save(postagem));
		
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put (@Valid @RequestBody Postagem postagem)
	{
		return ResponseEntity.status(HttpStatus.OK).body(rg.save(postagem));
		
	}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable long id)
	{
		rg.deleteById(id);
	}

}
