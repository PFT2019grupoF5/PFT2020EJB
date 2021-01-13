package com.services;
//
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.DAOservices.EntidadLocDAO;
import com.entities.EntidadLoc;
import com.exception.ServiciosException;

@Stateless
public class EntidadLocBean implements EntidadLocBeanRemote {

	@EJB
	private EntidadLocDAO entidadLocDAO;
   
	public EntidadLocBean() {
        
    }

	
	@Override
	public void add(EntidadLoc entidadLoc) throws ServiciosException {
		try{
			entidadLocDAO.add(entidadLoc);
		} catch (Exception e){
			throw new ServiciosException("Error en add EntidadLocBean : " + e.getMessage());
		}
	}

	@Override
	public void update(EntidadLoc entidadLoc) throws ServiciosException {
		try{
			entidadLocDAO.update(entidadLoc);

		} catch (Exception e){
			throw new ServiciosException("Error en update EntidadLocBean : " + e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try{			
			entidadLocDAO.delete(id);
		} catch (Exception e){
			throw new ServiciosException("Error en delete EntidadLocBean : " + e.getMessage());
		}
	}

	@Override
	public EntidadLoc getCodigo(int codigo) throws ServiciosException {
		try{
			return entidadLocDAO.getCodigo(codigo);
		}catch (Exception e) {
			throw new ServiciosException("Error en getCodigo EntidadLocBean : " + e.getMessage());
		}
	}
	
	@Override
	public EntidadLoc getNombre(String nombre) throws ServiciosException {
		try {
			return entidadLocDAO.getNombre(nombre);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}
	
	
	@Override
	public EntidadLoc getId(Long id) throws ServiciosException {
		try{
			return entidadLocDAO.getEntidadLoc(id);
		}catch (Exception e) {
			throw new ServiciosException("Error en getId EntidadLocBean : " + e.getMessage());
		}
		
	}
	
	
	
	@Override
	public EntidadLoc getEntidadLoc(Long id) throws ServiciosException {
		try{		
			return entidadLocDAO.getEntidadLoc(id);
		}catch(Exception e){
			throw new ServiciosException("No se pudo encontrar el Local >> " + e.getMessage());
		}
	}
	
	@Override
	public List<EntidadLoc> getAllEntidadesLoc() throws ServiciosException {
		try{		
			return entidadLocDAO.getAllEntidadLoc();
		}catch(Exception e){
			throw new ServiciosException("No se pudo obtener lista de Locales >> " + e.getMessage());
		}
	}
	
	@Override
	public int getLocalesxCiu(long idCiu) throws ServiciosException  {
		try{
			return entidadLocDAO.getLocalesxCiu(idCiu);
		}catch (Exception e) {
			throw new ServiciosException("Error en getId EntidadLocBean : " + e.getMessage());
		}
		
	}
	
	
}


