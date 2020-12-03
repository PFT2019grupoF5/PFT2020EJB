package com.services;

import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
			Almacenamiento u = new Almacenamiento();
			u.setVolumen(volumen);
			u.setNombre(nombre);
			u.setCostoop(costoop);
			u.setCapestiba(capestiba);
			u.setCappeso(cappeso);
			u.setEntidadLoc(entidadLoc);
			em.persist(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(int volumen, String nombre, double costoop, double capestiba, double cappeso, EntidadLoc entidadLoc) throws ServiciosException {
		try{
			Almacenamiento u = get(nombre);
			u.setVolumen(volumen);
			u.setCostoop(costoop);
			u.setCapestiba(capestiba);
			u.setCappeso(cappeso);
			u.setEntidadLoc(entidadLoc);
			em.merge(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(String nombre) throws ServiciosException {
		try{			
			em.remove(get(nombre));
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public Almacenamiento get(String nombre) throws ServiciosException {
		try{
			TypedQuery<Almacenamiento> query =  em.createNamedQuery("Almacenamiento.get", Almacenamiento.class)
					.setParameter("nombre", nombre);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
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


}
