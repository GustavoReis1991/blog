package com.blog.ggr.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.ggr.model.Usuario;
import com.blog.ggr.service.UsuarioService;
import com.blog.ggr.model.login;
import com.blog.ggr.repositorio.Usuariorepositorio;




@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class usuariocontroller {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired 
	private Usuariorepositorio userrep;
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> getAll() {
	
		return ResponseEntity.ok(usuarioService.listarusuario());

	}
	
	@PostMapping("/logar")
	public ResponseEntity<login> login(@RequestBody Optional<login> user) {
		return usuarioService.autenticarUsuario(user)
			.map(respostaAutenticacao -> ResponseEntity.ok(respostaAutenticacao))
			.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@RequestBody Usuario usuario) {

		return usuarioService.cadastrarUsuario(usuario)
			.map(respostaCadastro -> ResponseEntity.status(HttpStatus.CREATED).body(respostaCadastro))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	}

	


}
