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
			Pedido pe = new Pedido();
			pe.setPedfecestim(pedfecestim);
			pe.setFecha(fecha);
			pe.setPedreccodigo(pedreccodigo);
			pe.setPedrecfecha(pedrecfecha);
			pe.setPedreccomentario(pedreccomentario);
			pe.setPedestado(pedestado);
			pe.setUsuario(usuario);
			em.persist(pe);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, Date pedfecestim, Date fecha, int pedreccodigo, Date pedrecfecha, String pedreccomentario, estadoPedido pedestado, Usuario usuario) throws ServiciosException {
		try{
			Pedido pe = getId(id);
			pe.setPedfecestim(pedfecestim);
			pe.setFecha(fecha);
			pe.setPedreccodigo(pedreccodigo);
			pe.setPedrecfecha(pedrecfecha);
			pe.setPedreccomentario(pedreccomentario);
			pe.setPedestado(pedestado);
			pe.setUsuario(usuario);
			em.merge(pe);
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
	public Pedido getId(Long id) throws ServiciosException {
		try{
			TypedQuery<Pedido> query =  em.createNamedQuery("Pedido.getId", Pedido.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	@Override
	public LinkedList<Pedido> getComLike(String pedreccomentario) throws ServiciosException {
		LinkedList<Pedido> PedidosList = new LinkedList<>();
		try {
			TypedQuery<Pedido> query =  em.createNamedQuery("Pedido.getComLike", Pedido.class)
			.setParameter("pedreccomentario", "%"+pedreccomentario.toUpperCase()+"%");;
			PedidosList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return PedidosList;
	}
	
	
	@Override
	public LinkedList<Pedido> getAll() throws ServiciosException {
		LinkedList<Pedido> PedidosList = new LinkedList<>();
		try {
			TypedQuery<Pedido> query =  em.createNamedQuery("Pedido.getAll", Pedido.class);
			PedidosList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return PedidosList;
	}

    
}
