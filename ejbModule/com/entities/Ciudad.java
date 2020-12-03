package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Ciudad
 *
 */
@Entity
@Table(name="CIUDADES", schema="PROYECTO")
@NamedQueries({
	@NamedQuery(name="Ciudad.getAll", query="SELECT u FROM Ciudad u"),
	@NamedQuery(name="Ciudad.get", query="SELECT u FROM Ciudad u WHERE UPPER(u.nombre)=UPPER(:nombre)"),
	@NamedQuery(name="Ciudad.getNombreLike", query="SELECT u FROM Ciudad u WHERE UPPER(u.nombre) LIKE :nombre"),
})
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CIUDADES_IDCIUDAD_GENERATOR", sequenceName="SEQ_CIUDAD")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CIUDADES_IDCIUDAD_GENERATOR")
	@Column(name = "CIU_ID")
	private Long id;
	
	@Column(name = "CIU_NOMBRE", length=50, nullable=false, unique=true)
	private String nombre;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Ciudad() {
	}


}
