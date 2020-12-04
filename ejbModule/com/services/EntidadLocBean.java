package com.services;

import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entities.Ciudad;
import com.entities.EntidadLoc;
import com.enumerated.tipoLoc;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class EntidadLocBean
 */
@Stateless
public class EntidadLocBean implements EntidadLocBeanRemote {

    /**
     * Default constructor. 
     */
    public EntidadLocBean() {
        // TODO Auto-generated constructor stub
    }

    @PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(int codigo, String nombre, String direccion, tipoLoc tipoLoc, Ciudad ciudad) throws ServiciosException {
		try{
			EntidadLoc u = new EntidadLoc();
			u.setCodigo(codigo);
			u.setNombre(nombre);
			u.setDireccion(direccion);
			u.setTipoloc(tipoLoc);
			u.setCiudad(ciudad);
			em.persist(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(int codigo ,String nombre, String direccion, tipoLoc tipoLoc, Ciudad ciudad) throws ServiciosException {
		try{
			EntidadLoc u = get(codigo);
			u.setNombre(nombre);
			u.setDireccion(direccion);
			u.setTipoloc(tipoLoc);
			u.setCiudad(ciudad);
			em.merge(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(int codigo) throws ServiciosException {
		try{			
			em.remove(get(codigo));
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public EntidadLoc get(int codigo) throws ServiciosException {
		try{
			TypedQuery<EntidadLoc> query =  em.createNamedQuery("EntidadLoc.get", EntidadLoc.class)
					.setParameter("codigo", codigo);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public LinkedList<EntidadLoc> getAll() throws ServiciosException {
		LinkedList<EntidadLoc> EntidadLocList = new LinkedList<>();
		try {
			TypedQuery<EntidadLoc> query =  em.createNamedQuery("EntidadLoc.getAll", EntidadLoc.class);
			EntidadLocList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return EntidadLocList;
	}
}
