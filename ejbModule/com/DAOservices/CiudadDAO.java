package com.DAOservices;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Ciudad;
import com.exception.ServiciosException;
import javax.ejb.Stateless;


@Stateless
public class CiudadDAO {

	@PersistenceContext
	private EntityManager em;

	public CiudadDAO() {

	}

	
	public void add(Ciudad ciudad) throws ServiciosException {
		try {
			em.persist(ciudad);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public void update(Ciudad ciudad) throws ServiciosException {
		try {
			em.merge(ciudad);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try {
			Ciudad ciudad = em.find(Ciudad.class, id);
			em.remove(ciudad);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public Ciudad getId(Long id) throws ServiciosException {
		try {
			TypedQuery<Ciudad> query = em.createNamedQuery("Ciudad.getId", Ciudad.class).setParameter("id", id);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	public Ciudad getNombre(String nombre) throws ServiciosException {
		try {
			TypedQuery<Ciudad> query = em.createNamedQuery("Ciudad.getNombre", Ciudad.class).setParameter("nombre",
					nombre);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	public Ciudad getCiudad(Long id) throws ServiciosException {
		try {
			Ciudad ciudad = em.find(Ciudad.class, id);
			return ciudad;
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo encontrar la ciudad");
		}
	}

	public List<Ciudad> getAllCiudades() throws ServiciosException {
		try {
			TypedQuery<Ciudad> query = em.createNamedQuery("Ciudad.getAll", Ciudad.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se puede obtener lista de ciudades");
		}

	}

}
