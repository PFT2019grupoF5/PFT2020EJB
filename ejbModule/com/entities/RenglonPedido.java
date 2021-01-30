
package com.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: RenglonPedido
 */

@Entity
@Table(name="RENGLONESPEDIDOS", schema="PROYECTO", uniqueConstraints= {@UniqueConstraint(columnNames= {"REN_PRO_ID","REN_PED_ID"})})
@NamedQueries({
	@NamedQuery(name="RenglonPedido.getAll", query="SELECT rp FROM RenglonPedido rp"),
	@NamedQuery(name="RenglonPedido.getId",  query="SELECT rp FROM RenglonPedido rp WHERE rp.id=:id"),
	@NamedQuery(name="RenglonPedido.getRenglonxPedido", query="SELECT rp FROM RenglonPedido rp WHERE rp.pedido.id=:idPedido"),
})
public class RenglonPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RENGLONPEDIDOS_IDRENGLONPEDIDO_GENERATOR", sequenceName="SEQ_RENGLONPEDIDO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RENGLONPEDIDOS_IDRENGLONPEDIDO_GENERATOR")
	@Column(name = "REN_ID")
	private Long id;
	
	@Column(name = "REN_NRO", nullable=false)
	private int rennro;
	
	@Column(name = "REN_CANT", nullable=false)
	private int rencant;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "REN_PRO_ID")
	private Producto producto;
	
	@ManyToOne(optional=false)
	@JoinColumn(name = "REN_PED_ID")
	private Pedido pedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRennro() {
		return rennro;
	}

	public void setRennro(int rennro) {
		this.rennro = rennro;
	}

	public int getRencant() {
		return rencant;
	}

	public void setRencant(int rencant) {
		this.rencant = rencant;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public RenglonPedido() {
	}

}
