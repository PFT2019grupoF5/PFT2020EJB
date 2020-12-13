package com.DAOservices;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Almacenamiento;
import com.exception.ServiciosException;
import javax.ejb.Stateless;

@Stateless
public class AlmacenamientoDAO {

	@PersistenceContext
	private EntityManager em;

	public AlmacenamientoDAO() {

	}

	public void add(Almacenamiento almacenamiento) throws ServiciosException {
		try {
			em.persist(almacenamiento);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public void update(Almacenamiento almacenamiento) throws ServiciosException {
		try {
			em.merge(almacenamiento);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try {
			Almacenamiento almacenamiento = em.find(Almacenamiento.class, id);
			em.remove(almacenamiento);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public Almacenamiento getId(Long id) throws ServiciosException {
		try {
			TypedQuery<Almacenamiento> query = em.createNamedQuery("Almacenamiento.getId", Almacenamiento.class)
					.setParameter("id", id);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	public Almacenamiento getAlmacenamiento(Long id) throws ServiciosException {
		try {
			Almacenamiento almacenamiento = em.find(Almacenamiento.class, id);
			return almacenamiento;
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo encontrar el almacenamiento");
		}
	}

	public List<Almacenamiento> getAllAlmacenamientos() throws ServiciosException {
		try {
			TypedQuery<Almacenamiento> query = em.createNamedQuery("Almacenamiento.getAll", Almacenamiento.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo obtener lista de almacenamientos");
		}
	}

}
