package com.entities;

import java.io.Serializable;
import com.enumerated.estadoPedido;
import java.util.Date;

import javax.persistence.*;




public class RenglonReporte implements Serializable {

	private static final long serialVersionUID = 1L;


	private Date pedfecestim;
	private Date fecha;
	private int pedreccodigo;
	private estadoPedido pedestado;
	private Producto producto;
	private int rencant;

	
	
	
	public int getPedreccodigo() {
		return pedreccodigo;
	}
	public void setPedreccodigo(int pedreccodigo) {
		this.pedreccodigo = pedreccodigo;
	}

	public Date getPedfecestim() {
		return pedfecestim;
	}
	public void setPedfecestim(Date pedfecestim) {
		this.pedfecestim = pedfecestim;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public estadoPedido getPedestado() {
		return pedestado;
	}
	public void setPedestado(estadoPedido pedestado) {
		this.pedestado = pedestado;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getRencant() {
		return rencant;
	}
	public void setRencant(int rencant) {
		this.rencant = rencant;
	}
	

	public RenglonReporte() {
	}


}
