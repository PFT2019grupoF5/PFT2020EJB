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
			Familia u = new Familia();
			u.setNombre(nombre);
			u.setDescrip(descrip);
			u.setIncompat(incompat);
			em.persist(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(String nombre, String descrip, String incompat) throws ServiciosException {
		try{
			Familia u = get(nombre);
			u.setDescrip(descrip);
			u.setIncompat(incompat);
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
	public Familia get(String nombre) throws ServiciosException {
		try{
			TypedQuery<Familia> query =  em.createNamedQuery("Familia.get", Familia.class)
					.setParameter("nombre", nombre);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public LinkedList<Familia> getAll() throws ServiciosException {
		LinkedList<Familia> usuariosList = new LinkedList<>();
		try {
			TypedQuery<Familia> query =  em.createNamedQuery("Familia.getAll", Familia.class);
			usuariosList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return usuariosList;
	}
    
    
    
}
