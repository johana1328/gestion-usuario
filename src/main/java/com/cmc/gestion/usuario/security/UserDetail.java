package com.cmc.gestion.usuario.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserDetail {
	private String username;
	private List<String> roles;
	private Map<UserProperty,Object> aditionalInfo;
	
	public List<String> getAuthorities(){
		List<String> rolesAut= new ArrayList<String>();
		for(String in : roles) {
			rolesAut.add(in);
		}
		return rolesAut;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Map<UserProperty, Object> getAditionalInfo() {
		return aditionalInfo;
	}

	public void setAditionalInfo(Map<UserProperty, Object> aditionalInfo) {
		this.aditionalInfo = aditionalInfo;
	}
}
