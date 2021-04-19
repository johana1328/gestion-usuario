package com.cmc.gestion.usuario.bussines;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cmc.gestion.ControllerException;
import com.cmc.gestion.usuario.dao.PerfilDao;
import com.cmc.gestion.usuario.dao.UsuarioDao;
import com.cmc.gestion.usuario.dto.PaginationResponse;
import com.cmc.gestion.usuario.dto.PerfilDto;
import com.cmc.gestion.usuario.dto.UsuarioDto;
import com.cmc.gestion.usuario.entity.EstadoUsuario;
import com.cmc.gestion.usuario.entity.Perfil;
import com.cmc.gestion.usuario.entity.Usuario;

@Service
public class UsuarioBussines {
	
	@Autowired
	UsuarioDao usuarioDao;
	
	@Autowired
	PerfilDao perfilDao;
	
	public void crearUsuario(UsuarioDto usuario)  throws ControllerException{
		Optional<Usuario> optionalUsu = usuarioDao.findById(usuario.getIdUsuario());
		if (optionalUsu.isPresent()) {
			throw new ControllerException("El usuario se encuentra resgistrado");
		}
		Usuario usuEntity = new Usuario();
		usuEntity.setIdUsuario(usuario.getIdUsuario());
		usuEntity.setNombres(usuario.getNombres());
		usuEntity.setApellidos(usuario.getApellidos());
		usuEntity.setArea(usuario.getArea());
		usuEntity.setUsuario(usuario.getCorreoElectronico());
		usuEntity.setContrasena(usuario.getContrasena());
		usuEntity.setCorreoElectronico(usuario.getCorreoElectronico());
		usuEntity.setJefe(usuario.getJefe());
		usuEntity.setEstado(EstadoUsuario.USACT);
		
		List<Perfil> perfiles = new ArrayList<Perfil>();
		
		for(PerfilDto in: usuario.getUsPerfils()) {
			Perfil perfEntity = perfilDao.getById(in.getCodigoPerfil());
			perfiles.add(perfEntity);	
			
		}
		usuEntity.setUsPerfils(perfiles);
		usuarioDao.save(usuEntity);
		
		
	}
	
	public void modificarUsuario(UsuarioDto usuario) {
		Optional<Usuario> optionalUsu = usuarioDao.findById(usuario.getIdUsuario());
		if (optionalUsu.isPresent()) {
			Usuario usuEntity = optionalUsu.get();
			usuEntity.setNombres(usuario.getNombres());
			usuEntity.setApellidos(usuario.getApellidos());
			usuEntity.setArea(usuario.getArea());
			usuEntity.setUsuario(usuario.getCorreoElectronico());
			usuEntity.setCorreoElectronico(usuario.getCorreoElectronico());
			usuEntity.setJefe(usuario.getJefe());
			usuEntity.setEstado(EstadoUsuario.USACT);
			
			List<Perfil> perfiles = new ArrayList<Perfil>();
			
			for(PerfilDto in: usuario.getUsPerfils()) {
				Perfil perfEntity = perfilDao.getById(in.getCodigoPerfil());
				perfiles.add(perfEntity);	
				
			}
			
			usuEntity.setUsPerfils(perfiles);
			usuarioDao.save(usuEntity);
			
		}
		
	}
	

	public void cambiarEstadoUsuario(EstadoUsuario estado, String idUsuario) {
		Optional<Usuario> optionalUsuario = usuarioDao.findById(idUsuario);
		
		if (optionalUsuario.isPresent()) {
			
			Usuario usuarioEntity = optionalUsuario.get();
			
			usuarioEntity.setEstado(estado);
			
			usuarioDao.save(usuarioEntity);
	
		}
		
		
	}
	
	public PaginationResponse<UsuarioDto> listarUsuario(int pageSize, int pageKey, String sortBy){
		PaginationResponse<UsuarioDto> resp = new PaginationResponse<UsuarioDto>();
		Pageable paginacion = PageRequest.of(pageKey, pageSize,Sort.by(sortBy));
		Page<Usuario> usuarioPage = usuarioDao.findAll(paginacion);
		if (usuarioPage.hasContent()) {
			resp.setPageTotal(usuarioPage.getTotalPages());
			resp.setPageSize(pageSize);
			resp.setPageKey(pageKey+1);
			List<UsuarioDto> usuarios= buildListUsuario(usuarioPage.getContent());
			resp.setData(usuarios); 
			return resp;
		}else {
			return null;
		}
		
		
	}
	
	public PaginationResponse<UsuarioDto> getUsuariosPorFiltro(String id, String nombre, int pageKey, int pageSize, String sortBy){
		PaginationResponse<UsuarioDto> resp = new PaginationResponse<UsuarioDto>();
		Pageable paginacion = PageRequest.of(pageKey, pageSize);
		Page<Usuario> usuarioPage = usuarioDao.getUsuariosPorNombres(nombre, id, paginacion);
		if (usuarioPage.hasContent()) {
			resp.setPageTotal(usuarioPage.getTotalPages()); 
			resp.setPageSize(pageSize);
			resp.setPageKey(pageKey+1);
			List<UsuarioDto> usuarios= buildListUsuario(usuarioPage.getContent());
			resp.setData(usuarios); 
			return resp;
		}else {
			return null;
		}
	}
	
	public List<UsuarioDto> getUsuariosPorFiltro(String estado, String nombre){
		List<Usuario> listaBd = null;
		if(estado.equals("") && nombre.equals("")) {
			listaBd=(List<Usuario>) usuarioDao.findAll();		
		}else {
			listaBd = usuarioDao.getUsuariosReporte(nombre, estado);
		}
		if(!listaBd.isEmpty()) {
			List<UsuarioDto> usuarios= buildListUsuario(listaBd);
			return usuarios;
		}else {
			return null;
		}
	}
	
	public UsuarioDto getUsuarioById(String id) {
	    Optional<Usuario> optionalUsuario = usuarioDao.findById(id);
	    if (optionalUsuario.isPresent()) {
	    	UsuarioDto resp = buildUsuario(optionalUsuario.get());
	    	 return resp;
		}
	    return null;
	}
	
	public List<UsuarioDto> getAllUsers(){
		List<Usuario> listaUsuario=(List<Usuario>)this.usuarioDao.getAllUsuarios();
		if(!listaUsuario.isEmpty()) {
			List<UsuarioDto> usuarios= buildListUsuario(listaUsuario);
			return usuarios;
		}else {
			return null;
		}
	}
	
	
	private List<UsuarioDto> buildListUsuario(List<Usuario> listUsuarioEntity){
		List<UsuarioDto> resp= new ArrayList<UsuarioDto>();
		for(Usuario in: listUsuarioEntity) {
			UsuarioDto usuario = new UsuarioDto();
			usuario.setApellidos(in.getApellidos());
			usuario.setArea(in.getArea());
			usuario.setEstado(in.getEstado());
			usuario.setCorreoElectronico(in.getCorreoElectronico());
			usuario.setIdUsuario(in.getIdUsuario());
			usuario.setJefe(in.getJefe());
			usuario.setNombres(in.getNombres());
			usuario.setUsuario(in.getCorreoElectronico());
			
			List<PerfilDto> listPerfil = new ArrayList<PerfilDto>();
			for(Perfil inPerfil: in.getUsPerfils()) {
				PerfilDto perfil = new PerfilDto();
				perfil.setCodigoPerfil(inPerfil.getCodigoPerfil());
				perfil.setGrupoPerfil(inPerfil.getGrupoPerfil());
				perfil.setNombre(inPerfil.getNombre());
				listPerfil.add(perfil);
			}
			usuario.setUsPerfils(listPerfil);
			resp.add(usuario);
		}
		return resp;
		
	}

	private UsuarioDto buildUsuario(Usuario in){
			UsuarioDto usuario = new UsuarioDto();
			usuario.setApellidos(in.getApellidos());
			usuario.setArea(in.getArea());
			usuario.setEstado(in.getEstado());
			usuario.setCorreoElectronico(in.getCorreoElectronico());
			usuario.setIdUsuario(in.getIdUsuario());
			usuario.setJefe(in.getJefe());
			usuario.setNombres(in.getNombres());
			usuario.setUsuario(in.getCorreoElectronico());
			
			List<PerfilDto> listPerfil = new ArrayList<PerfilDto>();
			for(Perfil inPerfil: in.getUsPerfils()) {
				PerfilDto perfil = new PerfilDto();
				perfil.setCodigoPerfil(inPerfil.getCodigoPerfil());
				perfil.setGrupoPerfil(inPerfil.getGrupoPerfil());
				perfil.setNombre(inPerfil.getNombre());
				listPerfil.add(perfil);
			}
			usuario.setUsPerfils(listPerfil);
				return usuario;
		
	}
	

}

