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
import com.exception.ServiciosException;
import javax.ejb.Stateless;


@Stateless
public class ProductoDAO {
	
	 @PersistenceContext
	   	private EntityManager em;
	   	
		public ProductoDAO() {
			
		}
		

	   	public void add(Producto producto) throws ServiciosException {
	   		try{
	   			em.persist(producto);
	   			em.flush();
			}
			catch (PersistenceException e){
				throw new ServiciosException("Al crear un Producto se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
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
					throw new ServiciosException("Al crear un Producto se ha producido un error de validacion:"+ validationText);
				}
				throw new ServiciosException("Al crear un Producto se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
			}
	   		catch (Exception e){
	   			throw new ServiciosException("Error al crear Producto : " + e.getClass().getSimpleName() + ". " + e.getMessage());
	   		}
	   	}


	   	public void update(Producto producto) throws ServiciosException {
	   		try{
	   			em.merge(producto);
	   			em.flush();
			}
			catch (PersistenceException e){
				throw new ServiciosException("Al modificar un Producto se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
			}
			catch (ConstraintViolationException e) {
				throw new ServiciosException("Al modificar un Producto se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
			}
	   		catch (Exception e){
	   			throw new ServiciosException("Error al modificar Producto : " + e.getClass().getSimpleName() + ". " + e.getMessage());
	   		}
	   	}


	   	public void delete(Long id) throws ServiciosException {
	   		try{
	   			Producto producto = em.find(Producto.class,  id);
	   			em.remove(producto);
	   			em.flush();
			}
			catch (PersistenceException e){
				throw new ServiciosException("Al borrar un Producto se ha producido un error de persistencia : " + e.getClass().getSimpleName() + ". " + e.getMessage());
			}
			catch (ConstraintViolationException e) {
				throw new ServiciosException("Al borrar un Producto se ha producido un error de validacion : " + e.getClass().getSimpleName() + ". " + e.getMessage());
			}
	   		catch (Exception e){
	   			throw new ServiciosException("Error al borrar Producto : " + e.getClass().getSimpleName() + ". " + e.getMessage());
	   		}
	   	}
	   	

	   	public Producto getId(Long id) throws ServiciosException {
	   		try{
	   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.getId", Producto.class)
	   					.setParameter("id", id);
	   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
	   		}catch (Exception e) {
	   			throw new ServiciosException("Error al traer por Id Producto : " + e.getClass().getSimpleName() + ". " + e.getMessage());
	   		}

	   	}
	   	

	   	public Producto getNombre(String nombre) throws ServiciosException {
	   		try{
	   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.getNombre", Producto.class)
	   					.setParameter("nombre", nombre);
	   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
	   		}catch (Exception e) {
	   			throw new ServiciosException("Error al traer por Nombre Producto : " + e.getClass().getSimpleName() + ". " + e.getMessage());
	   		}
	   		
	   	}
	   	

		public Boolean validoBajaProductos(Producto producto) throws ServiciosException {
			
	   		try{
	   			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.getIdProd", Movimiento.class)
	   					.setParameter("producto", producto);
	   			return (query.getResultList().size()==0) ? false :  true;
	   		}catch (Exception e) {
	   			throw new ServiciosException("Error en validoBajaProductos : " + e.getClass().getSimpleName() + ". " + e.getMessage());
	   		}
	   	}

		
		public Producto getProducto(Long id) throws ServiciosException {
			try{		
				Producto producto = em.find(Producto.class, id); 
				return producto;
			}catch(Exception e){
				throw new ServiciosException("No se pudo encontrar el producto : " + e.getClass().getSimpleName() + ". " + e.getMessage());
			}
		}
		

		public List<Producto> getAllProductos() throws ServiciosException {
			try{		
				TypedQuery<Producto> query = em.createNamedQuery("Producto.getAll",Producto.class); 
				return query.getResultList();
			}catch(Exception e){
				throw new ServiciosException("No se pudo obtener lista de productos : " + e.getClass().getSimpleName() + ". " + e.getMessage());
			}
		}
		
		public int getProductosxFamilia(long idFam) throws ServiciosException{
			try {
				TypedQuery<Producto> query = em.createNamedQuery("Producto.getProductoxFamilia", Producto.class)
						.setParameter("idFam", idFam);
				return (query.getResultList().size());
			}catch (Exception e) {
				throw new ServiciosException("Error al traer los Productos por Id de Familia: " + e.getClass().getSimpleName() + ". " + e.getMessage());
			}
		}
		
}
