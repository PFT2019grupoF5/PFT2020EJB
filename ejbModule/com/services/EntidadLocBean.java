package com.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
			EntidadLoc el = new EntidadLoc();
			el.setCodigo(codigo);
			el.setNombre(nombre);
			el.setDireccion(direccion);
			el.setTipoloc(tipoLoc);
			el.setCiudad(ciudad);
			em.persist(el);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, int codigo ,String nombre, String direccion, tipoLoc tipoLoc, Ciudad ciudad) throws ServiciosException {
		try{
			EntidadLoc el = getId(id);
			el.setCodigo(codigo);
			el.setNombre(nombre);
			el.setDireccion(direccion);
			el.setTipoloc(tipoLoc);
			el.setCiudad(ciudad);
			em.merge(el);
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
	public EntidadLoc getCodigo(int codigo) throws ServiciosException {
		try{
			TypedQuery<EntidadLoc> query =  em.createNamedQuery("EntidadLoc.getCodigo", EntidadLoc.class)
					.setParameter("codigo", codigo);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public EntidadLoc getId(Long id) throws ServiciosException {
		try{
			TypedQuery<EntidadLoc> query =  em.createNamedQuery("EntidadLoc.getId", EntidadLoc.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public LinkedList<EntidadLoc> getCodigoLike(int codigo) throws ServiciosException {
		LinkedList<EntidadLoc> EntidadesLocList = new LinkedList<>();
		try {
			TypedQuery<EntidadLoc> query =  em.createNamedQuery("EntidadLoc.geCodigoLike", EntidadLoc.class)
			.setParameter("codigo", "%"+codigo+"%");;
			EntidadesLocList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return EntidadesLocList;
	}
	
	@Override
	public LinkedList<EntidadLoc> getAll() throws ServiciosException {
		LinkedList<EntidadLoc> EntidadesLocList = new LinkedList<>();
		try {
			TypedQuery<EntidadLoc> query =  em.createNamedQuery("EntidadLoc.getAll", EntidadLoc.class);
			EntidadesLocList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return EntidadesLocList;
	}
	@Override
	public EntidadLoc getEntidadLoc(Long id) throws ServiciosException {
		try{		
			EntidadLoc entidadLoc = em.find(EntidadLoc.class, id); 
			return entidadLoc;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el Local");
		}
	}
	
	@Override
	public List<EntidadLoc> getAllEntidadesLoc() throws ServiciosException {
		try{		
			TypedQuery<EntidadLoc> query = em.createQuery("SELECT el FROM EntidadLoc el",EntidadLoc.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de Locales");
		}
	}
	
	
}


