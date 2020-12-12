package com.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.DAOservices.RenglonPedidoDAO;
import com.entities.Pedido;
import com.entities.Producto;
import com.entities.RenglonPedido;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class RenglonPedidoBean implements RenglonPedidoBeanRemote {

	@EJB
	private RenglonPedidoDAO renglonPedidoDAO;

	public RenglonPedidoBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(int rennro, int rencant, Producto producto, Pedido pedido) throws ServiciosException {
		try {
			RenglonPedido r = new RenglonPedido();
			r.setRennro(rennro);
			r.setRencant(rencant);
			r.setProducto(producto);
			r.setPedido(pedido);
			renglonPedidoDAO.add(r);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, int rennro, int rencant, Producto producto, Pedido pedido) throws ServiciosException {
		try {
			RenglonPedido r = getId(id);
			r.setRennro(rennro);
			r.setRencant(rencant);
			r.setProducto(producto);
			r.setPedido(pedido);
			renglonPedidoDAO.update(id, r);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try {
			renglonPedidoDAO.delete(id);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public RenglonPedido getId(Long id) throws ServiciosException {
		try {
			return renglonPedidoDAO.getRenglonPedido(id);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	@Override
	public RenglonPedido getRenglonPedido(Long id) throws ServiciosException {
		try {
			return renglonPedidoDAO.getId(id);
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo encontrar el Renglon Pedido");
		}
	}

	@Override
	public List<RenglonPedido> getAllRenglonesPedido() throws ServiciosException {
		try {
			return renglonPedidoDAO.getAllRenglonesPedidos();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo obtener lista de Renglones de Pedido");
		}
	}

}
