package com.DAOservices;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Ciudad;
import com.exception.ServiciosException;

public class CiudadDAO {

	public CiudadDAO() {

	}

	@PersistenceContext
	private EntityManager em;

	public void add(Ciudad ciudad) throws ServiciosException {
		try {
			em.persist(ciudad);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public void update(Long id, Ciudad ciudad) throws ServiciosException {
		try {
			Ciudad c = getId(id);
			c.setNombre(ciudad.getNombre());
			em.merge(c);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try {
			em.remove(getId(id));
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
			TypedQuery<Ciudad> query = em.createQuery("Ciudad.getAll", Ciudad.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se puede obtener lista de ciudades");
		}

	}

}
