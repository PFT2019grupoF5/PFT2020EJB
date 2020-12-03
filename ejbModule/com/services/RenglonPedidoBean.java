package com.services;

import java.util.Date;
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
   			RenglonPedido u = new RenglonPedido();
   			u.setRennro(rennro);
   			u.setRencant(rencant);
   			u.setProducto(producto);
   			u.setPedido(pedido);
   			em.persist(u);
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public void update(Long id, int rennro, int rencant, Producto producto, Pedido pedido) throws ServiciosException {
   		try{
   			RenglonPedido u = get(id);
   			u.setRennro(rennro);
   			u.setRencant(rencant);
   			u.setProducto(producto);
   			u.setPedido(pedido);
   			em.merge(u);
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public void delete(Long id) throws ServiciosException {
   		try{			
   			em.remove(get(id));
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public RenglonPedido get(Long id) throws ServiciosException {
   		try{
   			TypedQuery<RenglonPedido> query =  em.createNamedQuery("RenglonPedido.get", RenglonPedido.class)
   					.setParameter("id", id);
   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
   		}catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		
   	}
   	
   	@Override
   	public LinkedList<RenglonPedido> getAll() throws ServiciosException {
   		LinkedList<RenglonPedido> RenglonPedidoList = new LinkedList<>();
   		try {
   			TypedQuery<RenglonPedido> query =  em.createNamedQuery("RenglonPedido.getAll", RenglonPedido.class);
   			RenglonPedidoList.addAll(query.getResultList());
   		} catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		return RenglonPedidoList;
   	}
}
