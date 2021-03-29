package com.cmc.gestion.usuario.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmc.gestion.usuario.ControllerException;
import com.cmc.gestion.usuario.bussines.UsuarioBussines;
import com.cmc.gestion.usuario.dto.UsuarioDto;
import com.cmc.gestion.usuario.entity.EstadoUsuario;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioBussines usuarioBussines;
	
	@GetMapping
	public List<UsuarioDto> listarUsuario(@RequestParam(defaultValue = "1") @Min(1) int pageKey,
			@RequestParam(defaultValue = "20") @Min(1) int pageSize,
			@RequestParam(defaultValue = "idUsuario" ) String sortBy,
			@RequestParam(name = "filterName",defaultValue = "")String nombre,
			@RequestParam(name = "filterId",defaultValue = "")String id){
		
		pageKey = pageKey-1;
		if ((!nombre.equals(""))|| (!id.equals(""))) {
			
			return usuarioBussines.getUsuariosPorFiltro(id, nombre, pageKey, pageSize, sortBy);
			
		}
		return usuarioBussines.listarUsuario(pageSize, pageKey, sortBy);
		
	}
	
	@PostMapping
	public void crearUsuario(@Valid @RequestBody UsuarioDto usuario) throws ControllerException {
		
		usuarioBussines.crearUsuario(usuario);	
		
	}
	
	@PutMapping("/{id}")
	public void modificarUsuario(@Valid @RequestBody UsuarioDto usuario, @PathVariable String id){
		usuarioBussines.modificarUsuario(usuario);
	}
	
	@PutMapping(path = "/activar/{id}/{estado}")
	public void cambiarEstadoUsuario(@PathVariable String id, @PathVariable EstadoUsuario estado) {
		usuarioBussines.cambiarEstadoUsuario(estado, id);
		
	}
	
	
	
	
	

}
