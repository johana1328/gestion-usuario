package com.cmc.gestion.usuario.bussines;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmc.gestion.usuario.dao.UsuarioDao;
import com.cmc.gestion.usuario.dto.LoginDto;
import com.cmc.gestion.usuario.entity.Usuario;

@Service
public class LoginBussines {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	public LoginDto login(LoginDto loginDto) {
		LoginDto resp = null;
		
		Optional<Usuario> optionalUsuario = usuarioDao.findByUsuarioAndContrasena(loginDto.getUser(), loginDto.getPassword());
		
		if (optionalUsuario.isPresent()) {
			
			resp = new LoginDto();
			
			resp.setUser(loginDto.getUser());
			resp.setToken("1234");
			
		}
		
		return resp;
				
	}

}
