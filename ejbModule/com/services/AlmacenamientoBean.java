package com.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

import com.DAOservices.AlmacenamientoDAO;
import com.entities.Almacenamiento;
import com.entities.EntidadLoc;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class AlmacenamientoBean
 */
@Stateless
public class AlmacenamientoBean implements AlmacenamientoBeanRemote {


	@EJB
	private AlmacenamientoDAO almacenamientoDAO; 

	public AlmacenamientoBean() {
        // TODO Auto-generated constructor stub
    }
	
	
	@Override
	public void add(int volumen, String nombre, double costoop, double capestiba, double cappeso, EntidadLoc entidadLoc) throws ServiciosException {
		try{
			Almacenamiento a = new Almacenamiento();
			a.setVolumen(volumen);
			a.setNombre(nombre);
			a.setCostoop(costoop);
			a.setCapestiba(capestiba);
			a.setCappeso(cappeso);
			a.setEntidadLoc(entidadLoc);
			almacenamientoDAO.add(a);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, int volumen, String nombre, double costoop, double capestiba, double cappeso, EntidadLoc entidadLoc) throws ServiciosException {
		try{
			Almacenamiento a = getId(id);
			a.setVolumen(volumen);
			a.setNombre(nombre);
			a.setCostoop(costoop);
			a.setCapestiba(capestiba);
			a.setCappeso(cappeso);
			a.setEntidadLoc(entidadLoc);
			almacenamientoDAO.update(id, a);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try{			
			almacenamientoDAO.delete(id);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public Almacenamiento getId(Long id) throws ServiciosException {
		try{
			return almacenamientoDAO.getId(id);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}
	
	
	@Override
	public Almacenamiento getAlmacenamiento(Long id) throws ServiciosException {
		try{		
			return almacenamientoDAO.getId(id);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el almacenamiento");
		}
	}
	
	@Override
	public List<Almacenamiento> getAllAlmacenamientos() throws ServiciosException {
		try{		
			return almacenamientoDAO.getAllAlmacenamientos();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de almacenamientos");
		}
	}


}
