package com.cmc.gestion.usuario.bussines;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cmc.gestion.usuario.dao.UsuarioDao;
import com.cmc.gestion.usuario.dto.LoginDto;
import com.cmc.gestion.usuario.entity.Perfil;
import com.cmc.gestion.usuario.entity.Usuario;
import com.cmc.gestion.usuario.security.JwtService;
import com.cmc.gestion.usuario.security.UserDetail;
import com.cmc.gestion.usuario.security.UserProperty;

@Service
public class LoginBussines {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
	private Environment env;
	
	public LoginDto login(LoginDto loginDto) {
		LoginDto resp = null;
		Optional<Usuario> optionalUsuario = usuarioDao.findByUsuarioAndContrasena(loginDto.getUser(), loginDto.getPassword());
		if (optionalUsuario.isPresent()) {
			resp = new LoginDto();
			resp.setUser(loginDto.getUser());
			String key=env.getProperty("com.cmc.security.key");
			int expiracion=Integer.parseInt(env.getProperty("com.cmc.security.timeout"));
			JwtService jwt = new JwtService(key, expiracion, null);  
			Usuario usuario=optionalUsuario.get();
			UserDetail userDetail=getUserDetails(usuario);
			String token=jwt.createToken(userDetail);
			resp.setToken(token);
			return resp;
		}else {
			return null;
		}		
	}
	
	private UserDetail getUserDetails(Usuario usuario) {
		UserDetail userDetails= new UserDetail();
		userDetails.setUsername(usuario.getNombres());
		List<String> perfiles= new ArrayList<String>();
		for(Perfil in :usuario.getUsPerfils()) {
			perfiles.add(in.getCodigoPerfil());
		}
		userDetails.setRoles(perfiles);
		Map<UserProperty, Object> aditionalInfo = new HashMap<UserProperty, Object>();
		aditionalInfo.put(UserProperty.EMAIL, usuario.getCorreoElectronico());
		aditionalInfo.put(UserProperty.ID_USER, usuario.getIdUsuario());
		aditionalInfo.put(UserProperty.JEFE, usuario.getJefe());
		aditionalInfo.put(UserProperty.NOMBRE, usuario.getNombres()+" "+usuario.getApellidos());
		userDetails.setAditionalInfo(aditionalInfo);
		return userDetails;
	}

}
