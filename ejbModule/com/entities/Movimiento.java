package com.entities;

import java.io.Serializable;
import javax.persistence.*;
import com.enumerated.tipoMovimiento;
import java.util.Date;


/*
 * Entity implementation class for Entity: Movimiento
 */
@Entity
@Table(name="MOVIMIENTOS", schema="PROYECTO")
@NamedQueries({
	@NamedQuery(name="Movimiento.getAll", query="SELECT m FROM Movimiento m"),
	@NamedQuery(name="Movimiento.getId",  query="SELECT m FROM Movimiento m WHERE m.id=:id"),
	@NamedQuery(name="Movimiento.getMovLike", query="SELECT m FROM Movimiento m WHERE UPPER(m.descripcion) = UPPER(:descripcion)"),
	@NamedQuery(name="Movimiento.validoBajaProducto", query="SELECT m FROM Movimiento m WHERE m.producto=:producto AND m.tipoMov=:tipoMov"),
	@NamedQuery(name="Movimiento.getMovimientoxAlmac",  query="SELECT m FROM Movimiento m WHERE m.almacenamiento.id=:idAlma"),
	
})
public class Movimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="MOVIMIENTOS_IDMOVIMIENTO_GENERATOR", sequenceName="SEQ_MOVIMIENTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MOVIMIENTOS_IDMOVIMIENTO_GENERATOR")
	@Column(name = "MOV_ID", nullable=false)
	private Long id;
	
	@Column(name = "MOV_FECHA", nullable=false)
	private Date fecha;
	
	@Column(name = "MOV_CANTIDAD", nullable=false)
	private int cantidad;
	
	@Column(length=250, name = "MOV_DESCRIPCION", nullable=false)
	private String descripcion;

//	@Column(name = "MOV_COSTO", nullable=false)
	@Column(name = "MOV_COSTO", nullable=false, columnDefinition = "FLOAT[5,2]")
	private double costo;
	
	@Column(name = "MOV_TIPO", length = 1, nullable=false)
	@Enumerated(value = EnumType.STRING)
	private tipoMovimiento tipoMov;

	@ManyToOne (optional = false)
	@JoinColumn(name = "MOV_PROD_ID")
	private Producto producto;
	
	@ManyToOne (optional = false)
	@JoinColumn(name = "MOV_ALMA_ID")
	private Almacenamiento almacenamiento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public tipoMovimiento getTipoMov() {
		return tipoMov;
	}

	public void setTipoMov(tipoMovimiento tipoMov) {
		this.tipoMov = tipoMov;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Almacenamiento getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(Almacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	public Movimiento() {

	}


	
}
