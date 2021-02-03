package com.DAOservices;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al crear una Ciudad se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			for(ConstraintViolation<?> cv: violations){
				//String messageTemplate=cv.getConstraintDescriptor().getMessageTemplate();
				String rootBean=cv.getRootBean().getClass().getSimpleName();
				String property=cv.getPropertyPath().toString();	
				String msg=cv.getMessage();//	"el nombre debe ser de menos de 50 caracteres"	
				String value=cv.getInvalidValue().toString();	
				String validationText= " En la entidad "+rootBean+" la propiedad '"+property+"' "+msg+":"+value;
				throw new ServiciosException("Al crear una Ciudad se ha producido un error de validacion:"+ validationText);
			}
			throw new ServiciosException("Al crear una Ciudad se ha producido un error de validacion : "  + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al crear Ciudad : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public void update(Ciudad ciudad) throws ServiciosException {
		try {
			em.merge(ciudad);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al modificar una Ciudad se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al modificar una Ciudad se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al modificar Ciudad : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try {
			Ciudad ciudad = em.find(Ciudad.class, id);
			em.remove(ciudad);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al borrar una Ciudad se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al borrar una Ciudad se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al borrar Ciudad : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public Ciudad getId(Long id) throws ServiciosException {
		try {
			TypedQuery<Ciudad> query = em.createNamedQuery("Ciudad.getId", Ciudad.class).setParameter("id", id);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException("Error al traer por Id Ciudad : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}

	}

	public Ciudad getNombre(String nombre) throws ServiciosException {
		try {
			TypedQuery<Ciudad> query = em.createNamedQuery("Ciudad.getNombre", Ciudad.class).setParameter("nombre",
					nombre);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException("Error al traer por Nombre Ciudad : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}

	}

	public Ciudad getCiudad(Long id) throws ServiciosException {
		try {
			Ciudad ciudad = new Ciudad();
			ciudad = em.find(Ciudad.class, id);
			return ciudad;
		} catch (Exception e) {
			throw new ServiciosException("No se pudo encontrar la ciudad : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public List<Ciudad> getAllCiudades() throws ServiciosException {
		try {
			TypedQuery<Ciudad> query = em.createNamedQuery("Ciudad.getAll", Ciudad.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new ServiciosException("No se puede obtener lista de ciudades : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}

	}

}
