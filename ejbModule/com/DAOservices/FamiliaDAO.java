package com.DAOservices;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Familia;
import com.exception.ServiciosException;
import javax.ejb.Stateless;


@Stateless
public class FamiliaDAO {

	@PersistenceContext
	private EntityManager em;

	public FamiliaDAO() {

	}


	public void add(Familia familia) throws ServiciosException {
		try {
			em.persist(familia);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public void update(Familia familia) throws ServiciosException {
		try {
			em.merge(familia);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try {
			Familia familia = em.find(Familia.class, id);
			em.remove(familia);
			em.flush();
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	public Familia getId(Long id) throws ServiciosException {
		try {
			TypedQuery<Familia> query = em.createNamedQuery("Familia.getId", Familia.class).setParameter("id", id);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	public Familia getNombre(String nombre) throws ServiciosException {
		try {
			TypedQuery<Familia> query = em.createNamedQuery("Familia.getNombre", Familia.class).setParameter("nombre",
					nombre);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	public Familia getFamilia(Long id) throws ServiciosException {
		try {
			Familia familia = em.find(Familia.class, id);
			return familia;
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo encontrar la familia");
		}
	}

	public List<Familia> getAllFamilias() throws ServiciosException {
		try {
			TypedQuery<Familia> query = em.createNamedQuery("Familia.getAll", Familia.class);
			return query.getResultList();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se puede obtener lista de familias");
		}

	}

}
