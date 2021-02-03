package com.DAOservices;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

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
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al crear una Familia se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
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
				throw new ServiciosException("Al crear una Familia se ha producido un error de validacion:"+ validationText);
			}
			throw new ServiciosException("Al crear una Familia se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al crear Familia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public void update(Familia familia) throws ServiciosException {
		try {
			em.merge(familia);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al modificar una Familia se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al modificar una Familia se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al modificar Familia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try {
			Familia familia = em.find(Familia.class, id);
			em.remove(familia);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al borrar una Familia se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al borrar una Familia se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al borrar Familia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public Familia getId(Long id) throws ServiciosException {
		try {
			TypedQuery<Familia> query = em.createNamedQuery("Familia.getId", Familia.class).setParameter("id", id);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException("Error al traer por Id Familia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}

	}

	public Familia getNombre(String nombre) throws ServiciosException {
		try {
			TypedQuery<Familia> query = em.createNamedQuery("Familia.getNombre", Familia.class).setParameter("nombre",
					nombre);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException("Error al traer por Nombre Familia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}

	}

	public Familia getFamilia(Long id) throws ServiciosException {
		try {
			Familia familia = em.find(Familia.class, id);
			return familia;
		} catch (Exception e) {
			throw new ServiciosException("No se pudo encontrar la familia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public List<Familia> getAllFamilias() throws ServiciosException {
		try {
			TypedQuery<Familia> query = em.createNamedQuery("Familia.getAll", Familia.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new ServiciosException("No se puede obtener lista de familias : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}

	}

}
