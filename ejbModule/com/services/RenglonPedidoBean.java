package com.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.DAOservices.RenglonPedidoDAO;
import com.entities.RenglonPedido;
import com.exception.ServiciosException;

@Stateless
public class RenglonPedidoBean implements RenglonPedidoBeanRemote {

	@EJB
	private RenglonPedidoDAO renglonPedidoDAO;

	public RenglonPedidoBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(RenglonPedido renglonPedido) throws ServiciosException {
		try {
			renglonPedidoDAO.add(renglonPedido);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(RenglonPedido renglonPedido) throws ServiciosException {
		try {
			renglonPedidoDAO.update(renglonPedido);
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
			return renglonPedidoDAO.getRenglonPedido(id);
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
