package com.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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
   			throw new ServiciosException("Error en add ProductoBean : " + e.getMessage());
   		}
   	}

   	@Override
   	public void update(Producto producto) throws ServiciosException {
   		try{
   			productoDAO.update(producto);
   		} catch (Exception e){
   			throw new ServiciosException("Error en update ProductoBean : " + e.getMessage());
   		}
   	}

   	@Override
   	public void delete(Long id) throws ServiciosException {
   		try{			
   			productoDAO.delete(id);
   		} catch (Exception e){
   			throw new ServiciosException("Error en delete ProductoBean : " + e.getMessage());
   		}
   	}
   	
   	@Override
   	public Producto getId(Long id) throws ServiciosException {
   		try{
   			return productoDAO.getId(id);
   		}catch (Exception e) {
   			throw new ServiciosException("Error en getId ProductoBean : " + e.getMessage());
   		}
   	}
   	
   	@Override
   	public Producto getNombre(String nombre) throws ServiciosException {
   		try{
   			return productoDAO.getNombre(nombre);
   		}catch (Exception e) {
   			throw new ServiciosException("Error en getNombre ProductoBean : " + e.getMessage());
   		}
   		
   	}

   	
   	@Override
	public Boolean validoBajaProductos(Producto producto) throws ServiciosException {
   		try{
   			return productoDAO.validoBajaProductos(producto);
   		}catch (Exception e) {
   			throw new ServiciosException("Error en validoBajaProductos ProductoBean : " + e.getMessage());
   		}
   	}

   	@Override
	public Producto getProducto(Long id) throws ServiciosException {
		try{		
			return productoDAO.getProducto(id);
		}catch(Exception e){
			throw new ServiciosException("No se pudo encontrar el producto >> " + e.getMessage());
		}
	}
	
	@Override
	public List<Producto> getAllProductos() throws ServiciosException {
		try{		 
			return productoDAO.getAllProductos();
		}catch(Exception e){
			throw new ServiciosException("No se pudo obtener lista de productos >> " + e.getMessage());
		}
	}
	
	@Override
	public int getProductosxFamilia (long idFam) throws ServiciosException {
		try {
			return productoDAO.getProductosxFamilia(idFam);
		}catch (Exception e) {
			throw new ServiciosException("Error en getId Familia: " +e.getMessage());
		}
	}
	
   	
}
