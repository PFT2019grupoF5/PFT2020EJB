package com.DAOservices;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Movimiento;
import com.exception.ServiciosException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@LocalBean
public class MovimientoDAO {

    public MovimientoDAO() {
    
    }
    
    @PersistenceContext
	private EntityManager em;
	

	public void add(Movimiento movimiento) throws ServiciosException {
		try{
			em.persist(movimiento);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	public void update(Long id, Movimiento movimiento) throws ServiciosException {
		try{
			Movimiento m = getId(id);
			m.setFecha(movimiento.getFecha());
			m.setCantidad(movimiento.getCantidad());
			m.setDescripcion(movimiento.getDescripcion());
			m.setCosto(movimiento.getCosto());
			m.setTipoMov(movimiento.getTipoMov());
			m.setProducto(movimiento.getProducto());
			m.setAlmacenamiento(movimiento.getAlmacenamiento());
			em.merge(m);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try{			
			em.remove(getId(id));
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	public Movimiento getId(Long id) throws ServiciosException {
		try{
			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.getId", Movimiento.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	public Movimiento getMovimiento(Long id) throws ServiciosException {
		try{		
			Movimiento movimiento = em.find(Movimiento.class, id); 
			return movimiento;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el movimiento");
		}
	}
	
	public List<Movimiento> getAllMovimientos() throws ServiciosException {
		try{		
			TypedQuery<Movimiento> query = em.createQuery("Movimiento.getAll",Movimiento.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de movimientos");
		}
	}
	

}
