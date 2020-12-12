package com.services;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.entities.Movimiento;
import com.DAOservices.MovimientoDAO;
import com.entities.Almacenamiento;
import com.entities.Producto;
import com.enumerated.tipoMovimiento;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class MovimientoBean
 */
@Stateless
public class MovimientoBean implements MovimientoBeanRemote {

	@EJB
	private MovimientoDAO movimientoDAO;
	
    public MovimientoBean() {

    }

    
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
			movimientoDAO.add(m);
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
			movimientoDAO.update(id, m);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try{			
			movimientoDAO.delete(id);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public Movimiento getId(Long id) throws ServiciosException {
		try{
			return movimientoDAO.getMovimiento(id);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public Movimiento getMovimiento(Long id) throws ServiciosException {
		try{		
			return movimientoDAO.getId(id);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el movimiento");
		}
	}
	
	@Override
	public List<Movimiento> getAllMovimientos() throws ServiciosException {
		try{		
			return movimientoDAO.getAllMovimientos();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de movimientos");
		}
	}
	

}
