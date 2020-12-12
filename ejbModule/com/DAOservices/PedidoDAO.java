package com.DAOservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Pedido;
import com.exception.ServiciosException;

public class PedidoDAO {
	
	public PedidoDAO() {

	}
	
	@PersistenceContext
	private EntityManager em;

	public void add(Pedido pedido) throws ServiciosException {
		try {
			em.persist(pedido);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public void update(Long id, Pedido pedido) throws ServiciosException {
		try {
			Pedido pe = getId(id);
			pe.setPedfecestim(pedido.getPedfecestim());
			pe.setFecha(pedido.getFecha());
			pe.setPedreccodigo(pedido.getPedreccodigo());
			pe.setPedrecfecha(pedido.getPedrecfecha());
			pe.setPedreccomentario(pedido.getPedreccomentario());
			pe.setPedestado(pedido.getPedestado());
			pe.setUsuario(pedido.getUsuario());
			em.merge(pe);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try {
			em.remove(getId(id));
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public Pedido getId(Long id) throws ServiciosException {
		try {
			TypedQuery<Pedido> query = em.createNamedQuery("Pedido.getId", Pedido.class).setParameter("id", id);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}



	public Pedido getPedido(Long id) throws ServiciosException {
		try {
			Pedido pedido = em.find(Pedido.class, id);
			return pedido;
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo encontrar el pedido");
		}
	}

	public List<Pedido> getAllPedidos() throws ServiciosException {
		try {
			TypedQuery<Pedido> query = em.createQuery("Pedido.getAll", Pedido.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se puede obtener lista de pedidos");
		}

	}
	
	private static java.sql.Date convert(java.util.Date uDate) {
	    java.sql.Date sDate = new java.sql.Date(uDate.getTime());
	    return sDate;
	}
	
	
	public List<Pedido> getPedidosEntreFechas(String fechaDesde, String fechaHasta) throws ServiciosException {
		TypedQuery<Pedido> query = null;
		try {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date fDesde = dateFormat.parse(fechaDesde);
				Date fHasta = dateFormat.parse(fechaHasta);

				java.sql.Date sqlfechaDesde = convert(fDesde);
				java.sql.Date sqlfechaHasta = convert(fHasta);

				query = em.createNamedQuery("Pedido.entreFechas", Pedido.class)
						.setParameter("fechaDesde", sqlfechaDesde).setParameter("fechaHasta", sqlfechaHasta);
				;

			} catch (ParseException ex) {
				throw new ServiciosException("No se pudo parsear fechas");
			}
			return query.getResultList();
		} catch (Exception e) {
			throw new ServiciosException("No se pudo obtener reporte de pedidos entre fechas");
		}
	}
	


}
