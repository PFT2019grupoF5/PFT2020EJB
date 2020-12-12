package com.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.DAOservices.EntidadLocDAO;
import com.entities.Ciudad;
import com.entities.EntidadLoc;
import com.enumerated.tipoLoc;
import com.exception.ServiciosException;

/**
 * Session Bean implementation class EntidadLocBean
 */
@Stateless
public class EntidadLocBean implements EntidadLocBeanRemote {

	@EJB
	private EntidadLocDAO entidadLocDAO;
   
	public EntidadLocBean() {
        
    }

 
	
	@Override
	public void add(int codigo, String nombre, String direccion, tipoLoc tipoLoc, Ciudad ciudad) throws ServiciosException {
		try{
			EntidadLoc el = new EntidadLoc();
			el.setCodigo(codigo);
			el.setNombre(nombre);
			el.setDireccion(direccion);
			el.setTipoloc(tipoLoc);
			el.setCiudad(ciudad);
			entidadLocDAO.add(el);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, int codigo ,String nombre, String direccion, tipoLoc tipoLoc, Ciudad ciudad) throws ServiciosException {
		try{
			EntidadLoc el = getId(id);
			el.setCodigo(codigo);
			el.setNombre(nombre);
			el.setDireccion(direccion);
			el.setTipoloc(tipoLoc);
			el.setCiudad(ciudad);
			entidadLocDAO.update(id, el);

		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try{			
			entidadLocDAO.delete(id);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public EntidadLoc getCodigo(int codigo) throws ServiciosException {
		try{
			return entidadLocDAO.getCodigo(codigo);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	@Override
	public EntidadLoc getId(Long id) throws ServiciosException {
		try{
			return entidadLocDAO.getEntidadLoc(id);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	
	
	@Override
	public EntidadLoc getEntidadLoc(Long id) throws ServiciosException {
		try{		
			return entidadLocDAO.getId(id);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el Local");
		}
	}
	
	@Override
	public List<EntidadLoc> getAllEntidadesLoc() throws ServiciosException {
		try{		
			return entidadLocDAO.getAllEntidadLoc();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de Locales");
		}
	}
	
	
}


