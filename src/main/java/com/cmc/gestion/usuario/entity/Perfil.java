package com.cmc.gestion.usuario.entity;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name="us_perfil")
public class Perfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="codigo_perfil")
	private String codigoPerfil;

	@Column(name="grupo_perfil")
	private String grupoPerfil;

	private String nombre;

	
	

	public Perfil() {
	}

	public String getCodigoPerfil() {
		return this.codigoPerfil;
	}

	public void setCodigoPerfil(String codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public String getGrupoPerfil() {
		return this.grupoPerfil;
	}

	public void setGrupoPerfil(String grupoPerfil) {
		this.grupoPerfil = grupoPerfil;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}