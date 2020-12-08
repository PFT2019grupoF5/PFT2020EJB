package com.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Almacenamiento;
import com.entities.EntidadLoc;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class AlmacenamientoBean
 */
@Stateless
public class AlmacenamientoBean implements AlmacenamientoBeanRemote {

    /**
     * Default constructor. 
     */
    public AlmacenamientoBean() {
        // TODO Auto-generated constructor stub
    }
    @PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(int volumen, String nombre, double costoop, double capestiba, double cappeso, EntidadLoc entidadLoc) throws ServiciosException {
		try{
			Almacenamiento a = new Almacenamiento();
			a.setVolumen(volumen);
			a.setNombre(nombre);
			a.setCostoop(costoop);
			a.setCapestiba(capestiba);
			a.setCappeso(cappeso);
			a.setEntidadLoc(entidadLoc);
			em.persist(a);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, int volumen, String nombre, double costoop, double capestiba, double cappeso, EntidadLoc entidadLoc) throws ServiciosException {
		try{
			Almacenamiento a = getId(id);
			a.setVolumen(volumen);
			a.setNombre(nombre);
			a.setCostoop(costoop);
			a.setCapestiba(capestiba);
			a.setCappeso(cappeso);
			a.setEntidadLoc(entidadLoc);
			em.merge(a);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try{			
			em.remove(getId(id));
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public Almacenamiento getId(Long id) throws ServiciosException {
		try{
			TypedQuery<Almacenamiento> query =  em.createNamedQuery("Almacenamiento.getId", Almacenamiento.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public LinkedList<Almacenamiento> getNombreLike(String nombre) throws ServiciosException {
		LinkedList<Almacenamiento> almacenamientosList = new LinkedList<>();
		try {
			TypedQuery<Almacenamiento> query =  em.createNamedQuery("Almacenamiento.getNombreLike", Almacenamiento.class)
			.setParameter("nombre", "%"+nombre.toUpperCase()+"%");;
			almacenamientosList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return almacenamientosList;
	}
	
	@Override
	public LinkedList<Almacenamiento> getAll() throws ServiciosException {
		LinkedList<Almacenamiento> almacenamientosList = new LinkedList<>();
		try {
			TypedQuery<Almacenamiento> query =  em.createNamedQuery("Almacenamiento.getAll", Almacenamiento.class);
			almacenamientosList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return almacenamientosList;
	}
	
	@Override
	public Almacenamiento getAlmacenamiento(Long id) throws ServiciosException {
		try{		
			Almacenamiento almacenamiento = em.find(Almacenamiento.class, id); 
			return almacenamiento;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el almacenamiento");
		}
	}
	
	@Override
	public List<Almacenamiento> getAllAlmacenamientos() throws ServiciosException {
		try{		
			TypedQuery<Almacenamiento> query = em.createQuery("SELECT a FROM Almacenamiento a",Almacenamiento.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de almacenamientos");
		}
	}


}
