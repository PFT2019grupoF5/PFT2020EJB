package com.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.entities.Producto;
import com.DAOservices.ProductoDAO;
import com.exception.ServiciosException;

@Stateless
@LocalBean
public class ProductoBean implements ProductoBeanRemote {
	

	@EJB
	private ProductoDAO productoDAO;
	
	
    public ProductoBean() {
        // TODO Auto-generated constructor stub
    }
  
   	@Override
   	public void add(Producto producto) throws ServiciosException {
   		try{
   			productoDAO.add(producto);
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public void update(Producto producto) throws ServiciosException {
   		try{
   			productoDAO.update(producto);
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public void delete(Long id) throws ServiciosException {
   		try{			
   			productoDAO.delete(id);
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}
   	
   	@Override
   	public Producto getId(Long id) throws ServiciosException {
   		try{
   			return productoDAO.getId(id);
   		}catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		

   		
   	}
   	
   	@Override
   	public Producto getNombre(String nombre) throws ServiciosException {
   		try{
   			return productoDAO.getNombre(nombre);
   		}catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		
   	}

   	
   	@Override
	public Boolean validoBajaProductos(Producto producto) throws ServiciosException {
   		try{
   			return productoDAO.validoBajaProductos(producto);
   		}catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
	public Producto getProducto(Long id) throws ServiciosException {
		try{		
			return productoDAO.getProducto(id);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el producto");
		}
	}
	
   	@Override
	public Producto getProductoByNombre(String nombre) throws ServiciosException {
		try{		
			return productoDAO.getProductoByNombre(nombre);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el producto");
		}
	}
	
	@Override
	public List<Producto> getAllProductos() throws ServiciosException {
		try{		 
			return productoDAO.getAllProductos();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de productos");
		}
	}
	
   	
}
