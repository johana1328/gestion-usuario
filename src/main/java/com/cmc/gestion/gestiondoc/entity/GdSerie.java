package com.cmc.gestion.gestiondoc.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


/**
 * The persistent class for the gd_serie database table.
 * 
 */
@Entity
@Table(name="gd_serie")
@NamedQuery(name="GdSerie.findAll", query="SELECT g FROM GdSerie g")
public class GdSerie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GD_SERIE_IDSERIE_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_serie")
	private int idSerie;

	@NotNull(message = "El campo de descripción es requerido")
	@NotEmpty(message = "El campo de descripción es requerido")
	private String descripcion;

	private String nombre;
	
	

	//bi-directional many-to-one association to GdSubserie
	//@OneToMany(mappedBy="gdSerie")
	//private List<GdSubserie> gdSubseries;

	public GdSerie() {
	}

	public int getIdSerie() {
		return this.idSerie;
	}

	public void setIdSerie(int idSerie) {
		this.idSerie = idSerie;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	public List<GdSubserie> getGdSubseries() {
//		return this.gdSubseries;
//	}
//
//	public void setGdSubseries(List<GdSubserie> gdSubseries) {
//		this.gdSubseries = gdSubseries;
//	}

//	public GdSubserie addGdSubsery(GdSubserie gdSubsery) {
//		getGdSubseries().add(gdSubsery);
//		gdSubsery.setGdSerie(this);
//
//		return gdSubsery;
//	}
//
//	public GdSubserie removeGdSubsery(GdSubserie gdSubsery) {
//		getGdSubseries().remove(gdSubsery);
//		gdSubsery.setGdSerie(null);
//
//		return gdSubsery;
//	}

}