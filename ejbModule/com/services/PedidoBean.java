package com.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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
	
	// Pedido.entreFechas
	
	@Override
	public LinkedList<Pedido> entreFechas(String fechaDesde, String fechaHasta) throws ServiciosException {
		TypedQuery<Pedido> query = em.createNamedQuery("Pedido.entreFechas", Pedido.class).setParameter("fechaDesde", fechaDesde).setParameter("fechaHasta", fechaHasta);;
		try {
			try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
	            Date fDesde = dateFormat.parse(fechaDesde);
	            Date fHasta = dateFormat.parse(fechaHasta);

		        java.sql.Date sqlfechaDesde = convert(fDesde);
		        java.sql.Date sqlfechaHasta = convert(fHasta);

				System.out.println("fechaDesde String:" + fDesde);
				System.out.println("fechaHasta String:" + fHasta);
				System.out.println("fechaDesde Date  :" + fDesde.toString());
				System.out.println("fechaHasta Date  :" + fDesde.toString());
				System.out.println("fechaDesde sql   :" + sqlfechaDesde.toString());
				System.out.println("fechaHasta sql   :" + sqlfechaHasta.toString());
				
			} catch (ParseException ex) {
			}
			return (LinkedList<Pedido>) query.getResultList();
		}catch (Exception e) {
			throw new ServiciosException("No se pudo obtener reporte de pedidos entre fechas");
		}
	}
	private static java.sql.Date convert(java.util.Date uDate) {
	    java.sql.Date sDate = new java.sql.Date(uDate.getTime());
	    return sDate;
	}
	
	@Override
	public List<Pedido> getPedidosEntreFechas(String fechaDesde, String fechaHasta) throws ServiciosException {
		TypedQuery<Pedido> query = em.createNamedQuery("Pedido.entreFechas", Pedido.class).setParameter("fechaDesde", fechaDesde).setParameter("fechaHasta", fechaHasta);;
		try {
			try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
	            Date fDesde = dateFormat.parse(fechaDesde);
	            Date fHasta = dateFormat.parse(fechaHasta);

		        java.sql.Date sqlfechaDesde = convert(fDesde);
		        java.sql.Date sqlfechaHasta = convert(fHasta);

				System.out.println("fechaDesde String:" + fDesde);
				System.out.println("fechaHasta String:" + fHasta);
				System.out.println("fechaDesde Date  :" + fDesde.toString());
				System.out.println("fechaHasta Date  :" + fDesde.toString());
				System.out.println("fechaDesde sql   :" + sqlfechaDesde.toString());
				System.out.println("fechaHasta sql   :" + sqlfechaHasta.toString());
				
			} catch (ParseException ex) {
			}
			return (List<Pedido>) query.getResultList();
		}catch (Exception e) {
			throw new ServiciosException("No se pudo obtener reporte de pedidos entre fechas");
		}
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

	@Override
	public Pedido getPedido(Long id) throws ServiciosException {
		try{		
			Pedido pedido = em.find(Pedido.class, id); 
			return pedido;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el almacenamiento");
		}
	}
	
	@Override
	public List<Pedido> getAllPedidos() throws ServiciosException {
		try{		
			TypedQuery<Pedido> query = em.createQuery("SELECT pe FROM Pedido pe",Pedido.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de pedidos");
		}
	}
	
    
}
