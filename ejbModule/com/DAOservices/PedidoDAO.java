package com.DAOservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import com.entities.Pedido;
import com.entities.RenglonReporte;
import com.exception.ServiciosException;
import javax.ejb.Stateless;

@Stateless
public class PedidoDAO {

	@PersistenceContext
	private EntityManager em;

	public PedidoDAO() {
	}

	public void add(Pedido pedido) throws ServiciosException {
		try {
			em.persist(pedido);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al crear un Pedido se ha producido un error de percistencia : " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al crear un Pedido se ha producido un error de validacion : " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al crear Pedido : " + e.getMessage());
		}
	}

	public void update(Pedido pedido) throws ServiciosException {
		try {
			em.merge(pedido);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al modificar un Pedido se ha producido un error de percistencia : " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al modificar un Pedido se ha producido un error de validacion : " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al modificar Pedido : " + e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try {
			Pedido pedido = em.find(Pedido.class, id);
			em.remove(pedido);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al borrar un Pedido se ha producido un error de percistencia : " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al borrar un Pedido se ha producido un error de validacion : " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al borrar Pedido : " + e.getMessage());
		}
	}

	public Pedido getId(Long id) throws ServiciosException {
		try {
			TypedQuery<Pedido> query = em.createNamedQuery("Pedido.getId", Pedido.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException("Error al traer por Id Pedido : " + e.getMessage());
		}

	}

	public Pedido getPedido(Long id) throws ServiciosException {
		try {
			Pedido pedido = em.find(Pedido.class, id);
			return pedido;
		} catch (Exception e) {
			throw new ServiciosException("No se pudo encontrar el pedido : " + e.getMessage());
		}
	}

	public List<Pedido> getAllPedidos() throws ServiciosException {
		try {
			TypedQuery<Pedido> query = em.createNamedQuery("Pedido.getAll", Pedido.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new ServiciosException("No se puede obtener lista de pedidos : " + e.getMessage());
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
				throw new ServiciosException("No se pudo parsear fechas : " + ex.getMessage());
			}
			return query.getResultList();
		} catch (Exception e) {
			throw new ServiciosException("No se pudo obtener reporte de pedidos entre fechas : " + e.getMessage());
		}
	}



	public List<RenglonReporte> getReporteEntreFechas(String fechaDesde, String fechaHasta) throws ServiciosException {
		TypedQuery<RenglonReporte> query = null;
		try {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date fDesde = dateFormat.parse(fechaDesde);
				Date fHasta = dateFormat.parse(fechaHasta);

				java.sql.Date sqlfechaDesde = convert(fDesde);
				java.sql.Date sqlfechaHasta = convert(fHasta);

				query = em.createNamedQuery("Pedido.reporteFechas", RenglonReporte.class)
						.setParameter("fechaDesde", sqlfechaDesde).setParameter("fechaHasta", sqlfechaHasta);
				;

			} catch (ParseException ex) {
				throw new ServiciosException("No se pudo parsear fechas : " + ex.getMessage());
			}
			return query.getResultList();
		} catch (Exception e) {
			throw new ServiciosException("No se pudo obtener reporte de pedidos entre fechas : " + e.getMessage());
		}
	}


}
