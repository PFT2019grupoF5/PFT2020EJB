package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Familia
 *
 */
@Entity
@Table(name="FAMILIAS", schema="PROYECTO")
@NamedQueries({
	@NamedQuery(name="Familia.getAll", query="SELECT f FROM Familia f"),
	@NamedQuery(name="Familia.getNombre", query="SELECT f FROM Familia f WHERE UPPER(f.nombre)=UPPER(:nombre)"),
	@NamedQuery(name="Familia.getNombreLike", query="SELECT f FROM Familia f WHERE UPPER(f.nombre) = UPPER(:nombre)"),
	@NamedQuery(name="Familia.getId", query="SELECT f FROM Familia f WHERE f.id=:id"),
})
public class Familia implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="FAMILIAS_IDFAMILIA_GENERATOR", sequenceName="SEQ_FAMILIA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FAMILIAS_IDFAMILIA_GENERATOR")
	@Column(name = "FAMI_CODI", nullable=false)
	private Long id;
	
	@Column(name = "FAMI_NOMBRE", length=50, nullable=false)
	private String nombre;
	
	@Column(name = "FAMI_DESCRIP", length=100, nullable=false)
	private String descrip;
	
	@Column(name = "FAMI_INCOMPAT", length=60, nullable=false)
	private String incompat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getIncompat() {
		return incompat;
	}

	public void setIncompat(String incompat) {
		this.incompat = incompat;
	}

	public Familia() {
	}

}
