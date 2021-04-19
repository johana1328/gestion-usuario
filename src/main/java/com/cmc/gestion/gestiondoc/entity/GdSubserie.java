package com.cmc.gestion.gestiondoc.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the gd_subserie database table.
 * 
 */
@Entity
@Table(name="gd_subserie")
@NamedQuery(name="GdSubserie.findAll", query="SELECT g FROM GdSubserie g")
public class GdSubserie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GD_SUBSERIE_ID_GENERATOR" )
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="disposicion_final")
	private String disposicionFinal;

	private String nombre;

	private String procedimiento;

	private String proceso;

	@Column(name="retencion_central")
	private int retencionCentral;

	@Column(name="retencion_gestion")
	private int retencionGestion;

	//bi-directional many-to-one association to GdSerie
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_serie")
	private GdSerie gdSerie;
//
//	//bi-directional many-to-one association to GdTipoDocumental
//	@OneToMany(mappedBy="gdSubserie")
//	private List<GdTipoDocumental> gdTipoDocumentals;

	public GdSubserie() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDisposicionFinal() {
		return this.disposicionFinal;
	}

	public void setDisposicionFinal(String disposicionFinal) {
		this.disposicionFinal = disposicionFinal;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProcedimiento() {
		return this.procedimiento;
	}

	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}

	public String getProceso() {
		return this.proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public int getRetencionCentral() {
		return this.retencionCentral;
	}

	public void setRetencionCentral(int retencionCentral) {
		this.retencionCentral = retencionCentral;
	}

	public int getRetencionGestion() {
		return this.retencionGestion;
	}

	public void setRetencionGestion(int retencionGestion) {
		this.retencionGestion = retencionGestion;
	}

	public GdSerie getGdSerie() {
		return this.gdSerie;
	}

	public void setGdSerie(GdSerie gdSerie) {
		this.gdSerie = gdSerie;
	}
//
//	public List<GdTipoDocumental> getGdTipoDocumentals() {
//		return this.gdTipoDocumentals;
//	}
//
//	public void setGdTipoDocumentals(List<GdTipoDocumental> gdTipoDocumentals) {
//		this.gdTipoDocumentals = gdTipoDocumentals;
//	}

//	public GdTipoDocumental addGdTipoDocumental(GdTipoDocumental gdTipoDocumental) {
//		getGdTipoDocumentals().add(gdTipoDocumental);
//		gdTipoDocumental.setGdSubserie(this);
//
//		return gdTipoDocumental;
//	}
//
//	public GdTipoDocumental removeGdTipoDocumental(GdTipoDocumental gdTipoDocumental) {
//		getGdTipoDocumentals().remove(gdTipoDocumental);
//		gdTipoDocumental.setGdSubserie(null);
//
//		return gdTipoDocumental;
//	}

}