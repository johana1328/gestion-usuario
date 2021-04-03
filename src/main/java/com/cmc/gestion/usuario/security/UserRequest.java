package com.cmc.gestion.usuario.security;

import java.util.Map;

public class UserRequest {
	
	private Map<UserProperty, Object> propiedUser;
	
	public void setPropiedUser(Map<UserProperty, Object> propiedUser) {
		this.propiedUser = propiedUser;
	}
	
	public Map<UserProperty, Object> getPropiedUser() {
		return propiedUser;
	}

	public Object getPropiedad(UserProperty propiedad) {
		if(this.propiedUser.containsKey(propiedad)) {
			return this.propiedUser.get(propiedad);
		}else {
			return null;
		}
		
	}

	
}
