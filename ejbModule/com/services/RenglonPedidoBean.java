package com.services;

import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Pedido;
import com.entities.Producto;
import com.entities.RenglonPedido;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class RenglonRenglonPedidoBean
 */
@Stateless
public class RenglonPedidoBean implements RenglonPedidoBeanRemote {

    /**
     * Default constructor. 
     */
    public RenglonPedidoBean() {
        // TODO Auto-generated constructor stub
    }

    @PersistenceContext
   	private EntityManager em;
   	
   	@Override
   	public void add(int rennro, int rencant, Producto producto, Pedido pedido) throws ServiciosException {
   		try{
   			RenglonPedido r = new RenglonPedido();
   			r.setRennro(rennro);
   			r.setRencant(rencant);
   			r.setProducto(producto);
   			r.setPedido(pedido);
   			em.persist(r);
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public void update(Long id, int rennro, int rencant, Producto producto, Pedido pedido) throws ServiciosException {
   		try{
   			RenglonPedido r = getId(id);
   			r.setRennro(rennro);
   			r.setRencant(rencant);
   			r.setProducto(producto);
   			r.setPedido(pedido);
   			em.merge(r);
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public void delete(Long id) throws ServiciosException {
   		try{			
   			em.remove(getId(id));
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public RenglonPedido getId(Long id) throws ServiciosException {
   		try{
   			TypedQuery<RenglonPedido> query =  em.createNamedQuery("RenglonPedido.getId", RenglonPedido.class)
   					.setParameter("id", id);
   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
   		}catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		
   	}
   	
   	@Override
   	public LinkedList<RenglonPedido> getAll() throws ServiciosException {
   		LinkedList<RenglonPedido> RenglonesPedidoList = new LinkedList<>();
   		try {
   			TypedQuery<RenglonPedido> query =  em.createNamedQuery("RenglonPedido.getAll", RenglonPedido.class);
   			RenglonesPedidoList.addAll(query.getResultList());
   		} catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		return RenglonesPedidoList;
   	}
}
