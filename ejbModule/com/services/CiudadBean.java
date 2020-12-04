package com.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entities.Ciudad;
import com.exception.ServiciosException;
import java.util.LinkedList;


/**
 * Session Bean implementation class CiudadBean
 */
@Stateless
public class CiudadBean implements CiudadBeanRemote {

    /**
     * Default constructor. 
     */
    public CiudadBean() {
        // TODO Auto-generated constructor stub
    }

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(String nombre) throws ServiciosException {
		try{
			Ciudad u = new Ciudad();
			u.setNombre(nombre);
			em.persist(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(String nombre) throws ServiciosException {
		try{
			Ciudad u = get(nombre);
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
	public Ciudad get(String nombre) throws ServiciosException {
		try{
			TypedQuery<Ciudad> query =  em.createNamedQuery("Ciudad.get", Ciudad.class)
					.setParameter("nombre", nombre);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public LinkedList<Ciudad> getAll() throws ServiciosException {
		LinkedList<Ciudad> usuariosList = new LinkedList<>();
		try {
			TypedQuery<Ciudad> query =  em.createNamedQuery("Ciudad.getAll", Ciudad.class);
			usuariosList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return usuariosList;
	}


}
