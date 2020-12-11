package com.services;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.entities.Producto;
import com.DAOservices.ProductoDAO;
import com.entities.Familia;
import com.entities.Usuario;
import com.enumerated.Segmentacion;
import com.exception.ServiciosException;

/*
 * Session Bean implementation class ProductoBean
 */
@Stateless
public class ProductoBean implements ProductoBeanRemote {
	

	@EJB
	private ProductoDAO productoDAO;
	
	
    public ProductoBean() {
        // TODO Auto-generated constructor stub
    }
  
   	@Override
   	public void add(String nombre, String lote, double precio, Date felab, Date fven, double peso, double volumen, int estiba, double stkMin, double stkTotal, Segmentacion segmentac, Usuario usuario, Familia familia) throws ServiciosException {
   		try{
   			Producto p = new Producto();
   			p.setNombre(nombre);
   			p.setLote(lote);
   			p.setPrecio(precio);
   			p.setFelab(felab);
   			p.setFven(fven);
   			p.setPeso(peso);
   			p.setVolumen(volumen);
   			p.setEstiba(estiba);
   			p.setStkMin(stkMin);
   			p.setStkTotal(stkTotal);
   			p.setSegmentac(segmentac);
   			p.setUsuario(usuario);
   			p.setFamilia(familia);
   			productoDAO.add(p);
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public void update(Long id, String nombre, String lote, double precio, Date felab, Date fven, double peso, double volumen, int estiba, double stkMin, double stkTotal, Segmentacion segmentac, Usuario usuario, Familia familia) throws ServiciosException {
   		try{
   			Producto p = getId(id);
   			p.setNombre(nombre);
   			p.setLote(lote);
   			p.setPrecio(precio);
   			p.setFelab(felab);
   			p.setFven(fven);
   			p.setPeso(peso);
   			p.setVolumen(volumen);
   			p.setEstiba(estiba);
   			p.setStkMin(stkMin);
   			p.setStkTotal(stkTotal);
   			p.setSegmentac(segmentac);
   			p.setUsuario(usuario);
   			p.setFamilia(familia);
   			productoDAO.update(id, p);
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
   			return productoDAO.getProducto(id);
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
			return productoDAO.getId(id);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el almacenamiento");
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
	
	//Edit
	
	/*public List<Producto> obtenerProductos() {
		try {

			return productoDAO.getAllProductos();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	*/

	//edit

	
   	
   	
}
