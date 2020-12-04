package com.services;

import java.util.Date;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.entities.Pedido;
import com.entities.Usuario;
import com.enumerated.estadoPedido;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class PedidoBean
 */
@Stateless
public class PedidoBean implements PedidoBeanRemote {

    /**
     * Default constructor. 
     */
    public PedidoBean() {
        // TODO Auto-generated constructor stub
    }

    @PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(Date pedfecestim, Date fecha, int pedreccodigo, Date pedrecfecha, String pedreccomentario, estadoPedido pedestado, Usuario usuario) throws ServiciosException {
		try{
			Pedido u = new Pedido();
			u.setPedfecestim(pedfecestim);
			u.setFecha(fecha);
			u.setPedreccodigo(pedreccodigo);
			u.setPedrecfecha(pedrecfecha);
			u.setPedreccomentario(pedreccomentario);
			u.setPedestado(pedestado);
			u.setUsuario(usuario);
			em.persist(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, Date pedfecestim, Date fecha, int pedreccodigo, Date pedrecfecha, String pedreccomentario, estadoPedido pedestado, Usuario usuario) throws ServiciosException {
		try{
			Pedido u = get(id);
			u.setPedfecestim(pedfecestim);
			u.setFecha(fecha);
			u.setPedreccodigo(pedreccodigo);
			u.setPedrecfecha(pedrecfecha);
			u.setPedreccomentario(pedreccomentario);
			u.setPedestado(pedestado);
			u.setUsuario(usuario);
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
	public Pedido get(Long id) throws ServiciosException {
		try{
			TypedQuery<Pedido> query =  em.createNamedQuery("Pedido.get", Pedido.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public LinkedList<Pedido> getAll() throws ServiciosException {
		LinkedList<Pedido> PedidoList = new LinkedList<>();
		try {
			TypedQuery<Pedido> query =  em.createNamedQuery("Pedido.getAll", Pedido.class);
			PedidoList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return PedidoList;
	}

    
}
