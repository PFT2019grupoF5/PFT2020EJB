package com.services;

import java.util.Date;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
			Movimiento u = new Movimiento();
			u.setFecha(fecha);
			u.setCantidad(cantidad);
			u.setDescripcion(descripcion);
			u.setCosto(costo);
			u.setTipoMov(tipoMov);
			u.setProducto(producto);
			u.setAlmacenamiento(almacenamiento);
			em.persist(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, Date fecha, int cantidad, String descripcion, double costo, tipoMovimiento tipoMov, Producto producto, Almacenamiento almacenamiento) throws ServiciosException {
		try{
			Movimiento u = get(id);
			u.setFecha(fecha);
			u.setCantidad(cantidad);
			u.setDescripcion(descripcion);
			u.setCosto(costo);
			u.setTipoMov(tipoMov);
			u.setProducto(producto);
			u.setAlmacenamiento(almacenamiento);
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
	public Movimiento get(Long id) throws ServiciosException {
		try{
			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.get", Movimiento.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public LinkedList<Movimiento> getAll() throws ServiciosException {
		LinkedList<Movimiento> almacenamientosList = new LinkedList<>();
		try {
			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.getAll", Movimiento.class);
			almacenamientosList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return almacenamientosList;
	}

}
