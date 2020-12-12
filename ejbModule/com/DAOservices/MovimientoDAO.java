package com.DAOservices;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Movimiento;
import com.exception.ServiciosException;
import javax.ejb.Stateless;


@Stateless
public class MovimientoDAO {

    @PersistenceContext
	private EntityManager em;
	
    public MovimientoDAO() {
        
    }

	public void add(Movimiento movimiento) throws ServiciosException {
		try{
			em.persist(movimiento);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	public void update(Movimiento movimiento) throws ServiciosException {
		try{
			em.merge(movimiento);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try{			
			Movimiento movimiento = em.find(Movimiento.class,  id);
			em.remove(movimiento);
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
			TypedQuery<Movimiento> query = em.createNamedQuery("Movimiento.getAll",Movimiento.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de movimientos");
		}
	}
	

}
