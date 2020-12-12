package com.DAOservices;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.RenglonPedido;
import com.exception.ServiciosException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@LocalBean
public class RenglonPedidoDAO {

	    /**
	     * Default constructor. 
	     */
	    public RenglonPedidoDAO() {
	        // TODO Auto-generated constructor stub
	    }

	    @PersistenceContext
	   	private EntityManager em;
	   	
	   	public void add(RenglonPedido renglonPedido) throws ServiciosException {
	   		try{
	   			em.persist(renglonPedido);
	   			em.flush();
	   		} catch (Exception e){
	   			throw new ServiciosException(e.getMessage());
	   		}
	   	}

	   	public void update(Long id, RenglonPedido renglonPedido) throws ServiciosException {
	   		try{
	   			RenglonPedido r = getId(id);
	   			r.setRennro(renglonPedido.getRennro());
	   			r.setRencant(renglonPedido.getRencant());
	   			r.setProducto(renglonPedido.getProducto());
	   			r.setPedido(renglonPedido.getPedido());
	   			em.merge(r);
	   			em.flush();
	   		} catch (Exception e){
	   			throw new ServiciosException(e.getMessage());
	   		}
	   	}

	   	public void delete(Long id) throws ServiciosException {
	   		try{			
	   			em.remove(getId(id));
	   			em.flush();
	   		} catch (Exception e){
	   			throw new ServiciosException(e.getMessage());
	   		}
	   	}

	   	public RenglonPedido getId(Long id) throws ServiciosException {
	   		try{
	   			TypedQuery<RenglonPedido> query =  em.createNamedQuery("RenglonPedido.getId", RenglonPedido.class)
	   					.setParameter("id", id);
	   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
	   		}catch (Exception e) {
	   			throw new ServiciosException(e.getMessage());
	   		}
	   		
	   	}
	   	
		public RenglonPedido getRenglonPedido(Long id) throws ServiciosException {
			try{		
				RenglonPedido renglonPedido = em.find(RenglonPedido.class, id); 
				return renglonPedido;
			}catch(PersistenceException e){
				throw new ServiciosException("No se pudo encontrar el renglon");
			}
		}
		

		public List<RenglonPedido> getAllRenglonesPedidos() throws ServiciosException {
			try{		
				TypedQuery<RenglonPedido> query = em.createQuery("RenglonPedido.getAll",RenglonPedido.class); 
				return query.getResultList();
			}catch(PersistenceException e){
				throw new ServiciosException("No se pudo obtener lista de Renglones de Pedido");
			}
		}
	   	
	

}
