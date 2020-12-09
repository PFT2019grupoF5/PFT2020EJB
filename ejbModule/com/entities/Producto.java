package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import com.enumerated.Segmentacion;
import java.util.Date;


/** 
 * Entity implementation class for Entity: Producto
 *
 */
@Entity
@Table(name="PRODUCTOS", schema="PROYECTO")
@NamedQueries({
	@NamedQuery(name="Producto.getAll", query="SELECT p FROM Producto p"),
	@NamedQuery(name="Producto.getNombre", query="SELECT p FROM Producto p WHERE UPPER(p.nombre)=UPPER(:nombre)"),
	@NamedQuery(name="Producto.getNombreLike", query="SELECT p FROM Producto p WHERE UPPER(p.nombre) = UPPER(:nombre)"),
	@NamedQuery(name="Producto.getId",  query="SELECT p FROM Producto p WHERE p.id=:id"),
})
public class Producto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="PRODUCTOS_IDPRODUCTO_GENERATOR", sequenceName="SEQ_PRODUCTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTOS_IDPRODUCTO_GENERATOR")
	@Column(name = "PROD_ID",nullable=false)
	private Long id;

	@Column(length=50,name = "PROD_NOMBRE", nullable=false)
	private String nombre;

	@Column(length=10,name = "PROD_LOTE", nullable=false)
	private String lote;

//	@Column(name = "PROD_PRECIO", nullable=false)
	@Column(name = "PROD_PRECIO", nullable=false, columnDefinition = "FLOAT[5,2]")
	private double precio;

	@Column(name = "PROD_FELAB", nullable=false)
	private Date felab;

	@Column(name = "PROD_FVEN", nullable=false)
	private Date fven;

//	@Column(name = "PROD_PESO", nullable=false)
	@Column(name = "PROD_PESO", nullable=false, columnDefinition = "FLOAT[5,2]")
	private double peso;

//	@Column(name = "PROD_VOL", nullable=false)
	@Column(name = "PROD_VOL", nullable=false, columnDefinition = "FLOAT[5,2]")
	private double volumen;

	@Column(name = "PROD_ESTIBA", nullable=false)
	private int estiba;

//	@Column(name = "PROD_STKMIN", nullable=false)
	@Column(name = "PROD_STKMIN", nullable=false, columnDefinition = "FLOAT[5,2]")
	private double stkMin;

//	@Column(name = "PROD_STKTOTAL", nullable=false)
	@Column(name = "PROD_STKTOTAL", nullable=false, columnDefinition = "FLOAT[5,2]")
	private double stkTotal;
	
	@Column(name = "PROD_SEGMENTAC", length = 1, nullable=false)
	@Enumerated(value = EnumType.STRING)
	private Segmentacion segmentac;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "PROD_USU_CODIGO")
	private Usuario usuario;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "PROD_FAMI_CODI")
	private Familia familia;

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

	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Date getFelab() {
		return felab;
	}

	public void setFelab(Date felab) {
		this.felab = felab;
	}

	public Date getFven() {
		return fven;
	}

	public void setFven(Date fven) {
		this.fven = fven;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getVolumen() {
		return volumen;
	}

	public void setVolumen(double volumen) {
		this.volumen = volumen;
	}

	public int getEstiba() {
		return estiba;
	}

	public void setEstiba(int estiba) {
		this.estiba = estiba;
	}

	public double getStkMin() {
		return stkMin;
	}

	public void setStkMin(double stkMin) {
		this.stkMin = stkMin;
	}

	public double getStkTotal() {
		return stkTotal;
	}

	public void setStkTotal(double stkTotal) {
		this.stkTotal = stkTotal;
	}

	public Segmentacion getSegmentac() {
		return segmentac;
	}

	public void setSegmentac(Segmentacion segmentac) {
		this.segmentac = segmentac;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Producto() {

	}



}
