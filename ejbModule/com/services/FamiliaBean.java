package com.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entities.Familia;
import com.exception.ServiciosException;
import java.util.LinkedList;


/**
 * Session Bean implementation class FamiliaBean
 */
@Stateless
public class FamiliaBean implements FamiliaBeanRemote {

    /**
     * Default constructor. 
     */
    public FamiliaBean() {
        // TODO Auto-generated constructor stub
    }

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(String nombre, String descrip, String incompat) throws ServiciosException {
		try{
			Familia f = new Familia();
			f.setNombre(nombre);
			f.setDescrip(descrip);
			f.setIncompat(incompat);
			em.persist(f);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, String nombre, String descrip, String incompat) throws ServiciosException {
		try{
			Familia f = getId(id);
			f.setNombre(nombre);
			f.setDescrip(descrip);
			f.setIncompat(incompat);
			em.merge(f);
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
	public Familia getNombre(String nombre) throws ServiciosException {
		try{
			TypedQuery<Familia> query =  em.createNamedQuery("Familia.getNombre", Familia.class)
					.setParameter("nombre", nombre);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public Familia getId(Long id) throws ServiciosException {
		try{
			TypedQuery<Familia> query =  em.createNamedQuery("Familia.getId", Familia.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public LinkedList<Familia> getNombreLike(String nombre) throws ServiciosException {
		LinkedList<Familia> FamiliasList = new LinkedList<>();
		try {
			TypedQuery<Familia> query =  em.createNamedQuery("Familia.getNombreLike", Familia.class)
			.setParameter("nombre", "%"+nombre.toUpperCase()+"%");;
			FamiliasList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return FamiliasList;
	}
	
	
	
	@Override
	public LinkedList<Familia> getAll() throws ServiciosException {
		LinkedList<Familia> FamiliasList = new LinkedList<>();
		try {
			TypedQuery<Familia> query =  em.createNamedQuery("Familia.getAll", Familia.class);
			FamiliasList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return FamiliasList;
	}
    
    
    
}
