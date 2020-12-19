package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/*
 * Entity implementation class for Entity: Almacenamiento
 *
 */
@Entity
@Table(name="ALMACENAMIENTOS", schema="PROYECTO")
@NamedQueries({
	@NamedQuery(name="Almacenamiento.getAll", query="SELECT a FROM Almacenamiento a"),
	@NamedQuery(name="Almacenamiento.getId", query="SELECT a FROM Almacenamiento a WHERE a.id=:id"),
	@NamedQuery(name="Almacenamiento.getNombreLike", query="SELECT a FROM Almacenamiento a WHERE UPPER(a.nombre) = UPPER(:nombre)"),
})
public class Almacenamiento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ALMACENAMIENTOS_IDALMACENAMIENTO_GENERATOR", sequenceName="SEQ_ALMACENAMIENTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ALMACENAMIENTOS_IDALMACENAMIENTO_GENERATOR")
	@Column(name = "ALMA_ID")
	private Long id;
	
	@Column(name = "ALMA_VOLUMEN", nullable =  false)
	private int volumen;

	@Column(name = "ALMA_DESCRIPCION", length=250, nullable=false, unique=true)
	private String nombre;
	
//	@Column(name = "ALMA_COSTOOP", nullable = false)
	@Column(name = "ALMA_COSTOOP", nullable = false, columnDefinition = "FLOAT(5,2)")
	private double costoop;

//	@Column(name = "ALMA_CAPESTIBA", nullable = false)
	@Column(name = "ALMA_CAPESTIBA", nullable = false, columnDefinition = "FLOAT(5,2)")
	private double capestiba;

//	@Column(name = "ALMA_CAPPESO", nullable = false)
	@Column(name = "ALMA_CAPPESO", nullable = false, columnDefinition = "FLOAT(5,2)")
	private double cappeso;

	@ManyToOne(optional=false)
	@JoinColumn(name = "ALMA_LOC_ID")
	private EntidadLoc entidadLoc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVolumen() {
		return volumen;
	}

	public void setVolumen(int volumen) {
		this.volumen = volumen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getCostoop() {
		return costoop;
	}

	public void setCostoop(double costoop) {
		this.costoop = costoop;
	}

	public double getCapestiba() {
		return capestiba;
	}

	public void setCapestiba(double capestiba) {
		this.capestiba = capestiba;
	}

	public double getCappeso() {
		return cappeso;
	}

	public void setCappeso(double cappeso) {
		this.cappeso = cappeso;
	}

	public EntidadLoc getEntidadLoc() {
		return entidadLoc;
	}

	public void setEntidadLoc(EntidadLoc entidadLoc) {
		this.entidadLoc = entidadLoc;
	}

	public Almacenamiento() {

	}



	
}
