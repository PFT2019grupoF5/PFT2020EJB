package com.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.DAOservices.PedidoDAO;
import com.entities.Pedido;
import com.exception.ServiciosException;

@Stateless
public class PedidoBean implements PedidoBeanRemote {

	@EJB
	private PedidoDAO pedidoDAO;

	public PedidoBean() {

	}

	@Override
	public void add(Pedido pedido) throws ServiciosException {
		try {
			pedidoDAO.add(pedido);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Pedido pedido) throws ServiciosException {
		try {
			pedidoDAO.update(pedido);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try {
			pedidoDAO.delete(id);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public Pedido getId(Long id) throws ServiciosException {
		try {
			return pedidoDAO.getPedido(id);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	@Override
	public List<Pedido> getPedidosEntreFechas(String fechaDesde, String fechaHasta) throws ServiciosException {
		try {
			return pedidoDAO.getPedidosEntreFechas(fechaDesde, fechaHasta);

		} catch (Exception e) {
			throw new ServiciosException("No se pudo obtener reporte de pedidos entre fechas");
		}
	}

	@Override
	public Pedido getPedido(Long id) throws ServiciosException {
		try {

			return pedidoDAO.getId(id);
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo encontrar el pedido");
		}
	}

	@Override
	public List<Pedido> getAllPedidos() throws ServiciosException {
		try {
			return pedidoDAO.getAllPedidos();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo obtener lista de pedidos");
		}
	}

}
