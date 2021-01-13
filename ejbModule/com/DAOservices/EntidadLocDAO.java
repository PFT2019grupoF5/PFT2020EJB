package com.DAOservices;
//
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;
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
			}
			catch (PersistenceException e){
				throw new ServiciosException("Al crear un Local se ha producido un error de percistencia : " + e.getMessage());
			}
			catch (ConstraintViolationException e) {
				throw new ServiciosException("Al crear un Local se ha producido un error de validacion : " + e.getMessage());
			}
			catch (Exception e) {
				throw new ServiciosException("Error al crear Local : " + e.getMessage());
			}
		}

		public void update(EntidadLoc entidadLoc) throws ServiciosException {
			try {
				em.merge(entidadLoc);
				em.flush();
			}
			catch (PersistenceException e){
				throw new ServiciosException("Al modificar un Local se ha producido un error de percistencia : " + e.getMessage());
			}
			catch (ConstraintViolationException e) {
				throw new ServiciosException("Al modificar un Local se ha producido un error de validacion : " + e.getMessage());
			}
			catch (Exception e) {
				throw new ServiciosException("Error al modificar Local : " + e.getMessage());
			}
		}

		public void delete(Long id) throws ServiciosException {
			try {
				EntidadLoc entidadLoc = em.find(EntidadLoc.class, id);
				em.remove(entidadLoc);
				em.flush();
			}
			catch (PersistenceException e){
				throw new ServiciosException("Al borrar un Local se ha producido un error de percistencia : " + e.getMessage());
			}
			catch (ConstraintViolationException e) {
				throw new ServiciosException("Al borrar un Local se ha producido un error de validacion : " + e.getMessage());
			}
			catch (Exception e) {
				throw new ServiciosException("Error al borrar Local : " +e.getMessage());
			}
		}

		public EntidadLoc getId(Long id) throws ServiciosException {
			try {
				TypedQuery<EntidadLoc> query = em.createNamedQuery("EntidadLoc.getId", EntidadLoc.class).setParameter("id", id);
				return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
			} catch (Exception e) {
				throw new ServiciosException("Error al traer por Id de Local : " + e.getMessage());
			}

		}

		public EntidadLoc getNombre(String nombre) throws ServiciosException {
			try {
				TypedQuery<EntidadLoc> query = em.createNamedQuery("EntidadLoc.getNombreLike", EntidadLoc.class).setParameter("nombre", nombre);
				return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
			} catch (Exception e) {
				throw new ServiciosException("Error al traer por Nombre de Local : " + e.getMessage());
			}

		}
			
		
		public EntidadLoc getCodigo(int codigo) throws ServiciosException {
			try {
				TypedQuery<EntidadLoc> query = em.createNamedQuery("EntidadLoc.getCodigo", EntidadLoc.class).setParameter("codigo",
						codigo);
				return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
			} catch (Exception e) {
				throw new ServiciosException("Error al traer por Codigo Local : " + e.getMessage());
			}

		}

		public int getLocalesxCiu(long idCiu) throws ServiciosException {
			try{
				TypedQuery<EntidadLoc> query =  em.createNamedQuery("EntidadLoc.getLocalesxCiu", EntidadLoc.class)
						.setParameter("idCiu", idCiu);
				
				return (query.getResultList().size());
			}catch (Exception e) {
				throw new ServiciosException("Error al traer los Locales por Id de Ciudades: " + e.getMessage());
			}
		}
		
		public EntidadLoc getEntidadLoc(Long id) throws ServiciosException {
			try {
				EntidadLoc entidadLoc = em.find(EntidadLoc.class, id);
				return entidadLoc;
			} catch (Exception e) {
				throw new ServiciosException("No se pudo encontrar el local : " + e.getMessage());
			}
		}

		public List<EntidadLoc> getAllEntidadLoc() throws ServiciosException {
			try {
				TypedQuery<EntidadLoc> query = em.createNamedQuery("EntidadLoc.getAll", EntidadLoc.class);
				return query.getResultList();
			} catch (Exception e) {
				throw new ServiciosException("No se puede obtener lista de locales : " + e.getMessage());
			}

		}
}
