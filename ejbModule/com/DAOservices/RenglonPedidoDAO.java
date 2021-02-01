package com.DAOservices;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.validation.ConstraintViolationException;

import com.entities.RenglonPedido;
import com.exception.ServiciosException;
import javax.ejb.Stateless;


@Stateless
public class RenglonPedidoDAO {

    @PersistenceContext
   	private EntityManager em;
  	
	    public RenglonPedidoDAO() {
	        // TODO Auto-generated constructor stub
	    }

	   	public void add(RenglonPedido renglonPedido) throws ServiciosException {
	   		try{
	   			em.persist(renglonPedido);
	   			em.flush();
			}
			catch (PersistenceException e){
				throw new ServiciosException("Al crear un RenglonPedido se ha producido un error de percistencia : " + e.getMessage());
			}
			catch (ConstraintViolationException e) {
				throw new ServiciosException("Al crear un RenglonPedido se ha producido un error de validacion : " + e.getMessage());
			}
	   		catch (Exception e){
	   			throw new ServiciosException("Error al crear RenglonPedido : " + e.getMessage());
	   		}
	   	}

	   	public void update(RenglonPedido renglonPedido) throws ServiciosException {
	   		try{
	   			em.merge(renglonPedido);
	   			em.flush();
			}
			catch (PersistenceException e){
				throw new ServiciosException("Al modificar un RenglonPedido se ha producido un error de percistencia : " + e.getMessage());
			}
			catch (ConstraintViolationException e) {
				throw new ServiciosException("Al modificar un RenglonPedido se ha producido un error de validacion : " + e.getMessage());
			}
	   		catch (Exception e){
	   			throw new ServiciosException("Error al modificar RenglonPedido : " + e.getMessage());
	   		}
	   	}

	   	public void delete(Long id) throws ServiciosException {
	   		try{
	   			RenglonPedido renglonPedido = em.find(RenglonPedido.class, id); 
	   			em.remove(renglonPedido);
	   			em.flush();
			}
			catch (PersistenceException e){
				throw new ServiciosException("Al borrar un RenglonPedido se ha producido un error de percistencia : " + e.getMessage());
			}
			catch (ConstraintViolationException e) {
				throw new ServiciosException("Al borrar un RenglonPedido se ha producido un error de validacion : " + e.getMessage());
			}
	   		catch (Exception e){
	   			throw new ServiciosException("Error al borrar RenglonPedido : " + e.getMessage());
	   		}
	   	}

	   	public RenglonPedido getId(Long id) throws ServiciosException {
	   		try{
	   			TypedQuery<RenglonPedido> query =  em.createNamedQuery("RenglonPedido.getId", RenglonPedido.class)
	   					.setParameter("id", id);
	   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
	   		}catch (Exception e) {
	   			throw new ServiciosException("Error al traer por Id RenglonPedido : " + e.getMessage());
	   		}
	   		
	   	}
	   	
		public RenglonPedido getRenglonPedido(Long id) throws ServiciosException {
			try{		
				RenglonPedido renglonPedido = em.find(RenglonPedido.class, id); 
				return renglonPedido;
			}catch(Exception e){
				throw new ServiciosException("No se pudo encontrar el renglon : " + e.getMessage());
			}
		}
		

		public List<RenglonPedido> getAllRenglonesPedidos() throws ServiciosException {
			try{		
				TypedQuery<RenglonPedido> query = em.createNamedQuery("RenglonPedido.getAll",RenglonPedido.class); 
				return query.getResultList();
			}catch(Exception e){
				throw new ServiciosException("No se pudo obtener lista de Renglones de Pedido : " + e.getMessage());
			}
		}
		
		public int getRenglonxPedido(Long idPedido) throws ServiciosException{
			try {
			TypedQuery<RenglonPedido> query = em.createNamedQuery("RenglonPedido.getRenglonxPedido", RenglonPedido.class).setParameter("idPedido", idPedido);
				return (query.getResultList().size());
				//
			}catch (Exception e) {
				throw new ServiciosException("Error al traer el Renglon de Pedido por Id de Pedido: " + e.getMessage());
			}
		}
	   	
	
		public List<RenglonPedido> getRenglonesDelPedido(Long idPedido) throws ServiciosException{
			try {
			TypedQuery<RenglonPedido> query = em.createNamedQuery("RenglonPedido.getRenglonxPedido", RenglonPedido.class).setParameter("idPedido", idPedido);
				return (query.getResultList());
				
			}catch (Exception e) {
				throw new ServiciosException("Error al traer los Renglones del Pedido por Id de Pedido: " + e.getMessage());
			}
		}
	   	

}
