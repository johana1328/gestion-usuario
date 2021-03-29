package com.cmc.gestion.usuario.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class LoginDto {
 
	@NotBlank(message = "El campo usuario es requerido")
	private String user;
	@NotBlank(message = "El campo password es requerido")
	 @JsonInclude(Include.NON_NULL)
	private String password;
	private String token;
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
