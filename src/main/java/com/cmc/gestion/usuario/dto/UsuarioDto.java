package com.cmc.gestion.usuario.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.cmc.gestion.usuario.entity.EstadoUsuario;

public class UsuarioDto {

	
	@NotBlank(message = "El campo IdUsuario es requerido")
	private String idUsuario;

	@NotBlank(message = "El campo apellido es requerido")
	private String apellidos;

	private String area;

	private String contrasena;

	@NotBlank(message = "El campo Correo Electronico es requerido")
	@Email(message = "El correo no esta bien escrito")
	private String correoElectronico;

	private EstadoUsuario estado;

	private String jefe;

	@NotBlank(message = "El campo nombre es requerido")
	private String nombres;

	private String usuario;
	
	private List<PerfilDto> usPerfils;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public EstadoUsuario getEstado() {
		return estado;
	}

	public void setEstado(EstadoUsuario estado) {
		this.estado = estado;
	}

	public String getJefe() {
		return jefe;
	}

	public void setJefe(String jefe) {
		this.jefe = jefe;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<PerfilDto> getUsPerfils() {
		return usPerfils;
	}

	public void setUsPerfils(List<PerfilDto> usPerfils) {
		this.usPerfils = usPerfils;
	}


}
