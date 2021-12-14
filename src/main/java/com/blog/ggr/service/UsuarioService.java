package com.blog.ggr.service;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.ggr.model.Usuario;
import com.blog.ggr.model.login;
import com.blog.ggr.repositorio.Usuariorepositorio;



@Service

public class UsuarioService {
	
	@Autowired
	private Usuariorepositorio userrep;
	
	public List<Usuario> listarusuario(){
		
		return userrep.findAll();
	}
	
	public Optional<Usuario> cadastrarUsuario (Usuario usuario){
		
		if (userrep.findByUsuario(usuario.getUsuario()).isPresent())
		return Optional.empty();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		String senhaencoder = encoder.encode(usuario.getSenha());
		
		usuario.setSenha(senhaencoder);
		
		return Optional.of(userrep.save(usuario));
	}
	
	public Optional<login> autenticarUsuario(Optional<login> usuarioLogin) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional<Usuario> usuario = userrep.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {
			
		
			if (encoder.matches(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {

				String auth = usuarioLogin.get().getUsuario() + ":" + usuarioLogin.get().getSenha();
							
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				
				String authHeader = "Basic " + new String(encodedAuth);

				usuarioLogin.get().setId(usuario.get().getId());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setSenha(usuario.get().getSenha());
				usuarioLogin.get().setToken(authHeader);

				return usuarioLogin;

			}
		}
		
		
		return Optional.empty();
	}
	
	

}
