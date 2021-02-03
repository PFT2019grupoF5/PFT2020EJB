package com.DAOservices;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.entities.Movimiento;
import com.entities.Producto;
import com.enumerated.tipoMovimiento;
import com.exception.ServiciosException;
import javax.ejb.Stateless;
//

@Stateless
public class MovimientoDAO {

    @PersistenceContext
	private EntityManager em;
	
    public MovimientoDAO() {
        
    }

	public void add(Movimiento movimiento) throws ServiciosException {
		try{
			em.persist(movimiento);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al crear un Movimiento se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			for(ConstraintViolation<?> cv: violations){
				//String messageTemplate=cv.getConstraintDescriptor().getMessageTemplate();
				String rootBean=cv.getRootBean().getClass().getSimpleName();
				String property=cv.getPropertyPath().toString();	
				String msg=cv.getMessage();//	"la descripcion debe ser de menos de 250 caracteres"	
				String value=cv.getInvalidValue().toString();	
				String validationText= " En la entidad "+rootBean+" la propiedad '"+property+"' "+msg+":"+value;
				throw new ServiciosException("Al crear un Movimiento se ha producido un error de validacion:"+ validationText);
			}
			throw new ServiciosException("Al crear un Movimiento se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e){
			throw new ServiciosException("Error al crear Movimiento : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public void update(Movimiento movimiento) throws ServiciosException {
		try{
			em.merge(movimiento);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al modificar un Movimiento se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al modificar un Movimiento se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e){
			throw new ServiciosException("Error al modificar Movimiento : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public void delete(Long id) throws ServiciosException {
		try{			
			Movimiento movimiento = em.find(Movimiento.class,  id);
			em.remove(movimiento);
			em.flush();
		}
		catch (PersistenceException e){
			throw new ServiciosException("Al borrar un Movimiento se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (ConstraintViolationException e) {
			throw new ServiciosException("Al borrar un Movimiento se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		catch (Exception e){
			throw new ServiciosException("Error al borrar Movimiento : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}

	public int getMovimientoxAlmac(long idAlma) throws ServiciosException {
		try{
			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.getMovimientoxAlmac", Movimiento.class)
					.setParameter("idAlma", idAlma);
			
			return (query.getResultList().size());
		}catch (Exception e) {
			throw new ServiciosException("Error al traer el Movimiento por Id de Almacenamiento: " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		
	}

	
	public Movimiento getId(Long id) throws ServiciosException {
		try{
			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.getId", Movimiento.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException("Error al traer por Id Movimiento : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		
	}
	
	public Movimiento validoBajaProducto(Producto producto) throws ServiciosException {
		try{
			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.validoBajaProducto", Movimiento.class)
					.setParameter("producto", producto)
					.setParameter("tipoMov", tipoMovimiento.valueOf("P"));
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException("Error al validoBajaProducto : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
		
	}
	
	public Movimiento getMovimiento(Long id) throws ServiciosException {
		try{		
			Movimiento movimiento = em.find(Movimiento.class, id); 
			return movimiento;
		}catch(Exception e){
			throw new ServiciosException("No se pudo encontrar el movimiento : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}
	
	public List<Movimiento> getAllMovimientos() throws ServiciosException {
		try{		
			TypedQuery<Movimiento> query = em.createNamedQuery("Movimiento.getAll",Movimiento.class); 
			return query.getResultList();
		}catch(Exception e){
			throw new ServiciosException("No se pudo obtener lista de movimientos : " + e.getClass().getSimpleName() + ". " + e.getMessage());
		}
	}
	

}
