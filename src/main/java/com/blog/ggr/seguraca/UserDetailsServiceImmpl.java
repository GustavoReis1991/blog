package com.blog.ggr.seguraca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.ggr.model.Usuario;
import com.blog.ggr.repositorio.Usuariorepositorio;

@Service
public class UserDetailsServiceImmpl implements UserDetailsService {
	
	@Autowired
	private Usuariorepositorio userRep;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<Usuario> user = userRep.findByUsuario(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + "not found."));
		
		return user.map(UserDetailslmpl :: new).get();
		
	}

}
