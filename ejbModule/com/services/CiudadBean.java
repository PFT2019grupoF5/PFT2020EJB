package com.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Ciudad;
import com.exception.ServiciosException;
import java.util.LinkedList;
import java.util.List;


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
			Ciudad c = new Ciudad();
			c.setNombre(nombre);
			em.persist(c);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, String nombre) throws ServiciosException {
		try{
			Ciudad c = getId(id);
			c.setNombre(nombre);
			em.merge(c);
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
	public Ciudad getNombre(String nombre) throws ServiciosException {
		try{
			TypedQuery<Ciudad> query =  em.createNamedQuery("Ciudad.getNombre", Ciudad.class)
					.setParameter("nombre", nombre);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}

	@Override
	public Ciudad getId(Long id) throws ServiciosException {
		try{
			TypedQuery<Ciudad> query =  em.createNamedQuery("Ciudad.getId", Ciudad.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public LinkedList<Ciudad> getNombreLike(String nombre) throws ServiciosException {
		LinkedList<Ciudad> CiudadesList = new LinkedList<>();
		try {
			TypedQuery<Ciudad> query =  em.createNamedQuery("Ciudad.getNombreLike", Ciudad.class)
			.setParameter("nombre", "%"+nombre.toUpperCase()+"%");;
			CiudadesList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return CiudadesList;
	}
	
	@Override
	public LinkedList<Ciudad> getAll() throws ServiciosException {
		LinkedList<Ciudad> CiudadesList = new LinkedList<>();
		try {
			TypedQuery<Ciudad> query =  em.createNamedQuery("Ciudad.getAll", Ciudad.class);
			CiudadesList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return CiudadesList;
	}
	
	@Override
	public Ciudad getCiudad(Long id) throws ServiciosException {
		try{		
			Ciudad ciudad = em.find(Ciudad.class, id); 
			return ciudad;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar la ciudad");
		}
	}
	
	@Override
	public List<Ciudad> getAllCiudades() throws ServiciosException{
		try {
			TypedQuery<Ciudad> query = em.createQuery("SELECT c FROM Ciudad c", Ciudad.class);
			return query.getResultList();
		}catch(PersistenceException e) {
			throw new ServiciosException("No se puede obtener lista de ciudades");
		}
		
	}


}
