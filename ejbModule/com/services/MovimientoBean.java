package com.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.entities.Movimiento;
import com.DAOservices.MovimientoDAO;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class MovimientoBean implements MovimientoBeanRemote {

	@EJB
	private MovimientoDAO movimientoDAO;
	
    public MovimientoBean() {

    }

    
	@Override
	public void add(Movimiento movimiento) throws ServiciosException {
		try{
			movimientoDAO.add(movimiento);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Movimiento movimiento) throws ServiciosException {
		try{
			movimientoDAO.update(movimiento);
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
			return movimientoDAO.getMovimiento(id);
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
