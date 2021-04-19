package com.cmc.gestion.gestiondoc.entity;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the gd_tipo_documental database table.
 * 
 */
@Entity
@Table(name="gd_tipo_documental")
@NamedQuery(name="GdTipoDocumental.findAll", query="SELECT g FROM GdTipoDocumental g")
public class GdTipoDocumental implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GD_TIPO_DOCUMENTAL_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.AUTO, generator="GD_TIPO_DOCUMENTAL_ID_GENERATOR")
	private int id;

	private String nombre;

	@Column(name="tipo_documento")
	private String tipoDocumento;

	//bi-directional many-to-one association to GdProcesoTipoDocumento
	

	//bi-directional many-to-one association to GdSubserie
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_subserie")
	private GdSubserie gdSubserie;

	public GdTipoDocumental() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	

	public GdSubserie getGdSubserie() {
		return this.gdSubserie;
	}

	public void setGdSubserie(GdSubserie gdSubserie) {
		this.gdSubserie = gdSubserie;
	}

}