package com.DAOservices;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al crear un Almacenamiento se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			for(ConstraintViolation<?> cv: violations){
				//String messageTemplate=cv.getConstraintDescriptor().getMessageTemplate();
				String rootBean=cv.getRootBean().getClass().getSimpleName();
				String property=cv.getPropertyPath().toString();	
				String msg=cv.getMessage();//	"el nombre debe ser de menos de 250 caracteres"	
				String value=cv.getInvalidValue().toString();	
				String validationText= " En la entidad "+rootBean+" la propiedad '"+property+"' "+msg+":"+value;
				throw new ServiciosException("Al crear un Almacenamiento se ha producido un error de validacion:"+ validationText);
			}
			throw new ServiciosException("Al crear un Almacenamiento se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al crear Almacenamiento : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public void update(Almacenamiento almacenamiento) throws ServiciosException {
		try {
			em.merge(almacenamiento);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al modificar un Almacenamiento se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al modificar un Almacenamiento se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al modificar Almacenamiento : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try {
			Almacenamiento almacenamiento = em.find(Almacenamiento.class, id);
			em.remove(almacenamiento);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al borrar un Almacenamiento se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al borrar un Almacenamiento se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e) {
			throw new ServiciosException("Error al borrar Almacenamiento : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public int getAlmacenamientoxLoc(Long idLoc) throws ServiciosException {
		try{
			TypedQuery<Almacenamiento> query =  em.createNamedQuery("Almacenamiento.getAlmacenamientoxLoc", Almacenamiento.class)
					.setParameter("idLoc", idLoc);
			
			return (query.getResultList().size());
		}catch (Exception e) {
			throw new ServiciosException("Error al traer el Almacenamiento por Id de Local: " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		
	}
	
	public Almacenamiento getId(Long id) throws ServiciosException {
		try {
			TypedQuery<Almacenamiento> query = em.createNamedQuery("Almacenamiento.getId", Almacenamiento.class)
					.setParameter("id", id);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException("Error al traer por Id Almacenamiento : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public Almacenamiento getNombre(String nombre) throws ServiciosException {
		try {
			TypedQuery<Almacenamiento> query = em.createNamedQuery("Almacenamiento.getNombreLike", Almacenamiento.class)
					.setParameter("nombre", nombre);
			return (query.getResultList().size() == 0) ? null : query.getResultList().get(0);
		} catch (Exception e) {
			throw new ServiciosException("Error al traer por Nombre de Almacenamiento: " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}
	
	public Almacenamiento getAlmacenamiento(Long id) throws ServiciosException {
		try {
			Almacenamiento almacenamiento = em.find(Almacenamiento.class, id);
			return almacenamiento;
		} catch (Exception e) {
			throw new ServiciosException("No se pudo encontrar el almacenamiento : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public List<Almacenamiento> getAllAlmacenamientos() throws ServiciosException {
		try {
			TypedQuery<Almacenamiento> query = em.createNamedQuery("Almacenamiento.getAll", Almacenamiento.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new ServiciosException("No se pudo obtener lista de almacenamientos : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}
	
}
