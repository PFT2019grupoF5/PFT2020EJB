package com.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Movimiento;
import com.entities.Almacenamiento;
import com.entities.Producto;
import com.enumerated.tipoMovimiento;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class MovimientoBean
 */
@Stateless
public class MovimientoBean implements MovimientoBeanRemote {

    /**
     * Default constructor. 
     */
    public MovimientoBean() {
        // TODO Auto-generated constructor stub
    }
    @PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(Date fecha, int cantidad, String descripcion, double costo, tipoMovimiento tipoMov, Producto producto, Almacenamiento almacenamiento) throws ServiciosException {
		try{
			Movimiento m = new Movimiento();
			m.setFecha(fecha);
			m.setCantidad(cantidad);
			m.setDescripcion(descripcion);
			m.setCosto(costo);
			m.setTipoMov(tipoMov);
			m.setProducto(producto);
			m.setAlmacenamiento(almacenamiento);
			em.persist(m);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, Date fecha, int cantidad, String descripcion, double costo, tipoMovimiento tipoMov, Producto producto, Almacenamiento almacenamiento) throws ServiciosException {
		try{
			Movimiento m = getId(id);
			m.setFecha(fecha);
			m.setCantidad(cantidad);
			m.setDescripcion(descripcion);
			m.setCosto(costo);
			m.setTipoMov(tipoMov);
			m.setProducto(producto);
			m.setAlmacenamiento(almacenamiento);
			em.merge(m);
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
	public Movimiento getId(Long id) throws ServiciosException {
		try{
			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.getId", Movimiento.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public LinkedList<Movimiento> getMovLike(String descripcion) throws ServiciosException {
		LinkedList<Movimiento> movimientosList = new LinkedList<>();
		try {
			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.getMovLike", Movimiento.class)
			.setParameter("descripcion", "%"+descripcion.toUpperCase()+"%");;
			movimientosList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return movimientosList;
	}
	
	
	@Override
	public LinkedList<Movimiento> getAll() throws ServiciosException {
		LinkedList<Movimiento> movimientosList = new LinkedList<>();
		try {
			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.getAll", Movimiento.class);
			movimientosList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return movimientosList;
	}
	@Override
	public Movimiento getMovimiento(Long id) throws ServiciosException {
		try{		
			Movimiento movimiento = em.find(Movimiento.class, id); 
			return movimiento;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el movimiento");
		}
	}
	
	@Override
	public List<Movimiento> getAllMovimientos() throws ServiciosException {
		try{		
			TypedQuery<Movimiento> query = em.createQuery("SELECT m FROM Movimiento m",Movimiento.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de movimientos");
		}
	}
	

}
