package com.DAOservices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
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
			throw new ServiciosException("Al crear un Pedido se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			for(ConstraintViolation<?> cv: violations){
				//String messageTemplate=cv.getConstraintDescriptor().getMessageTemplate();
				String rootBean=cv.getRootBean().getClass().getSimpleName();
				String property=cv.getPropertyPath().toString();	
				String msg=cv.getMessage();//	"el comentario debe ser de menos de 250 caracteres"	
				String value=cv.getInvalidValue().toString();	
				String validationText= " En la entidad "+rootBean+" la propiedad '"+property+"' "+msg+":"+value;
				throw new ServiciosException("Al crear un Pedido se ha producido un error de validacion:"+ validationText);
			}
			throw new ServiciosException("Al crear un Pedido se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al crear Pedido : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public void update(Pedido pedido) throws ServiciosException {
		try {
			em.merge(pedido);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al modificar un Pedido se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al modificar un Pedido se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al modificar Pedido : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try {
			Pedido pedido = em.find(Pedido.class, id);
			em.remove(pedido);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al borrar un Pedido se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al borrar un Pedido se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al borrar Pedido : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public Pedido getId(Long id) throws ServiciosException {
		try {
			TypedQuery<Pedido> query = em.createNamedQuery("Pedido.getId", Pedido.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException("Error al traer por Id Pedido : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}

	}

	public Pedido getPedido(Long id) throws ServiciosException {
		try {
			Pedido pedido = em.find(Pedido.class, id);
			return pedido;
		} catch (Exception e) {
			throw new ServiciosException("No se pudo encontrar el pedido : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public List<Pedido> getAllPedidos() throws ServiciosException {
		try {
			TypedQuery<Pedido> query = em.createNamedQuery("Pedido.getAll", Pedido.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new ServiciosException("No se puede obtener lista de pedidos : " + e.getClass().getSimpleName() + ". " + e.getMessage());
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
			throw new ServiciosException("No se pudo obtener reporte de pedidos entre fechas : " + e.getClass().getSimpleName() + ". " + e.getMessage());
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

				query = em.createQuery("SELECT pe.pedreccodigo, pe.fecha, pe.pedfecestim, pe.pedestado, rp.producto, rp.rencant FROM Pedido pe, RenglonPedido rp WHERE pe.id = rp.pedido.id AND pe.fecha BETWEEN :fechaDesde AND :fechaHasta ORDER BY pe.fecha ASC", RenglonReporte.class)
						.setParameter("fechaDesde", sqlfechaDesde).setParameter("fechaHasta", sqlfechaHasta);
				

			} catch (ParseException ex) {
				throw new ServiciosException("No se pudo parsear fechas : " + ex.getMessage());
			}
			return query.getResultList();
		} catch (Exception e) {
			throw new ServiciosException("No se pudo obtener reporte de pedidos entre fechas : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}


}
