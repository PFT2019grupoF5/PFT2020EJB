package com.services;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.DAOservices.PedidoDAO;
import com.entities.Pedido;
import com.entities.Usuario;
import com.enumerated.estadoPedido;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class PedidoBean
 */
@Stateless
public class PedidoBean implements PedidoBeanRemote {

	@EJB
	private PedidoDAO pedidoDAO;
    
	public PedidoBean() {
    
    }

	
	@Override
	public void add(Date pedfecestim, Date fecha, int pedreccodigo, Date pedrecfecha, String pedreccomentario, estadoPedido pedestado, Usuario usuario) throws ServiciosException {
		try{
			Pedido pe = new Pedido();
			pe.setPedfecestim(pedfecestim);
			pe.setFecha(fecha);
			pe.setPedreccodigo(pedreccodigo);
			pe.setPedrecfecha(pedrecfecha);
			pe.setPedreccomentario(pedreccomentario);
			pe.setPedestado(pedestado);
			pe.setUsuario(usuario);
			pedidoDAO.add(pe);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, Date pedfecestim, Date fecha, int pedreccodigo, Date pedrecfecha, String pedreccomentario, estadoPedido pedestado, Usuario usuario) throws ServiciosException {
		try{
			Pedido pe = getId(id);
			pe.setPedfecestim(pedfecestim);
			pe.setFecha(fecha);
			pe.setPedreccodigo(pedreccodigo);
			pe.setPedrecfecha(pedrecfecha);
			pe.setPedreccomentario(pedreccomentario);
			pe.setPedestado(pedestado);
			pe.setUsuario(usuario);
			pedidoDAO.update(id, pe);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try{			
			pedidoDAO.delete(id);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public Pedido getId(Long id) throws ServiciosException {
		try{
			return pedidoDAO.getPedido(id);
		}catch (Exception e) {
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
		try{		
			
			return pedidoDAO.getId(id);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el pedido");
		}
	}
	
	@Override
	public List<Pedido> getAllPedidos() throws ServiciosException {
		try{					
			return pedidoDAO.getAllPedidos();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de pedidos");
		}
	}
	
    
}
