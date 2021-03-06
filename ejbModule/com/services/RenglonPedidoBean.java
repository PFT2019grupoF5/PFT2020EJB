package com.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import com.DAOservices.RenglonPedidoDAO;
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
	public void add(RenglonPedido renglonPedido) throws ServiciosException {
		try {
			renglonPedidoDAO.add(renglonPedido);
		} catch (Exception e) {
			throw new ServiciosException("Error en add RenglonPedidoBean : " + e.getMessage());
		}
	}

	@Override
	public void update(RenglonPedido renglonPedido) throws ServiciosException {
		try {
			renglonPedidoDAO.update(renglonPedido);
		} catch (Exception e) {
			throw new ServiciosException("Error en update RenglonPedidoBean : " + e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try {
			renglonPedidoDAO.delete(id);
		} catch (Exception e) {
			throw new ServiciosException("Error en delete RenglonPedidoBean : " + e.getMessage());
		}
	}

	@Override
	public RenglonPedido getId(Long id) throws ServiciosException {
		try {
			return renglonPedidoDAO.getRenglonPedido(id);
		} catch (Exception e) {
			throw new ServiciosException("Error en getId RenglonPedidoBean : " + e.getMessage());
		}

	}
	
	@Override
	public RenglonPedido getRen(int rennro) throws ServiciosException {
		try {
			return renglonPedidoDAO.getRen(rennro);
		} catch (Exception e) {
			throw new ServiciosException("Error en getRen RenglonPedidoBean : " + e.getMessage());
		}

	}

	@Override
	public RenglonPedido getRenglonPedido(Long id) throws ServiciosException {
		try {
			return renglonPedidoDAO.getRenglonPedido(id);
		} catch (Exception e) {
			throw new ServiciosException("No se pudo encontrar el Renglon Pedido >> " + e.getMessage());
		}
	}

	@Override
	public List<RenglonPedido> getAllRenglonesPedido() throws ServiciosException {
		try {
			return renglonPedidoDAO.getAllRenglonesPedidos();
		} catch (Exception e) {
			throw new ServiciosException("No se pudo obtener lista de Renglones de TODOS los Pedidos >> " + e.getMessage());
		}
	}

	@Override
	public int getRenglonxPedido(Long idPedido) throws ServiciosException {
		try {
			return renglonPedidoDAO.getRenglonxPedido(idPedido);
		}catch(Exception e) {
			throw new ServiciosException("No se puede encontrar el pedido >>" + e.getMessage());
			}
	}
	
	@Override
	public int getRenglonxProducto(Long idProducto) throws ServiciosException {
		try {
			return renglonPedidoDAO.getRenglonxProducto(idProducto);
		}catch(Exception e) {
			throw new ServiciosException("No se puede encontrar el producto >>" + e.getMessage());
			}
	}

	@Override
	public List<RenglonPedido> getRenglonesDelPedido(Long idPedido) throws ServiciosException {
		try {
			return renglonPedidoDAO.getRenglonesDelPedido(idPedido);
		} catch (Exception e) {
			throw new ServiciosException("No se pudo obtener lista de Renglones del Pedido >> " + e.getMessage());
		}
	}

}
