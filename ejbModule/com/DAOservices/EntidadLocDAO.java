package com.DAOservices;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.EntidadLoc;
import com.exception.ServiciosException;
import javax.ejb.Stateless;


@Stateless
public class EntidadLocDAO {


		@PersistenceContext
		private EntityManager em;

		public EntidadLocDAO() {

		}

		public void add(EntidadLoc entidadLoc) throws ServiciosException {
			try {
				em.persist(entidadLoc);
				em.flush();
			} catch (Exception e) {
				throw new ServiciosException(e.getMessage());
			}
		}

		public void update(EntidadLoc entidadLoc) throws ServiciosException {
			try {
				em.merge(entidadLoc);
				em.flush();
			} catch (Exception e) {
				throw new ServiciosException(e.getMessage());
			}
		}

		public void delete(Long id) throws ServiciosException {
			try {
				EntidadLoc entidadLoc = new EntidadLoc();
				em.remove(entidadLoc);
				em.flush();
			} catch (Exception e) {
				throw new ServiciosException(e.getMessage());
			}
		}

		public EntidadLoc getId(Long id) throws ServiciosException {
			try {
				TypedQuery<EntidadLoc> query = em.createNamedQuery("EntidadLoc.getId", EntidadLoc.class).setParameter("id", id);
				return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
			} catch (Exception e) {
				throw new ServiciosException(e.getMessage());
			}

		}

		public EntidadLoc getCodigo(int codigo) throws ServiciosException {
			try {
				TypedQuery<EntidadLoc> query = em.createNamedQuery("EntidadLoc.getCodigo", EntidadLoc.class).setParameter("codigo",
						codigo);
				return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
			} catch (Exception e) {
				throw new ServiciosException(e.getMessage());
			}

		}

		public EntidadLoc getEntidadLoc(Long id) throws ServiciosException {
			try {
				EntidadLoc entidadLoc = em.find(EntidadLoc.class, id);
				return entidadLoc;
			} catch (PersistenceException e) {
				throw new ServiciosException("No se pudo encontrar el local");
			}
		}

		public List<EntidadLoc> getAllEntidadLoc() throws ServiciosException {
			try {
				TypedQuery<EntidadLoc> query = em.createNamedQuery("EntidadLoc.getAll", EntidadLoc.class);
				return query.getResultList();
			} catch (PersistenceException e) {
				throw new ServiciosException("No se puede obtener lista de locales");
			}

		}
}
