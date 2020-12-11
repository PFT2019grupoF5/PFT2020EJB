package com.DAOservices;


import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Movimiento;
import com.entities.Producto;
import com.exception.ServiciosException;

public class ProductoDAO {
	
	public ProductoDAO() {
		
	}
	
	 @PersistenceContext
	   	private EntityManager em;
	   	

	   	public void add(Producto producto) throws ServiciosException {
	   		try{
	   			em.persist(producto);
	   			em.flush();
	   		} catch (Exception e){
	   			throw new ServiciosException(e.getMessage());
	   		}
	   	}


	   	public void update(Long id, Producto producto) throws ServiciosException {
	   		try{
	   			Producto p = getId(id);
	   			p.setNombre(producto.getNombre());
	   			p.setLote(producto.getLote());
	   			p.setPrecio(producto.getPrecio());
	   			p.setFelab(producto.getFelab());
	   			p.setFven(producto.getFven());
	   			p.setPeso(producto.getPeso());
	   			p.setVolumen(producto.getVolumen());
	   			p.setEstiba(producto.getEstiba());
	   			p.setStkMin(producto.getStkMin());
	   			p.setStkTotal(producto.getStkTotal());
	   			p.setSegmentac(producto.getSegmentac());
	   			p.setUsuario(producto.getUsuario());
	   			p.setFamilia(producto.getFamilia());
	   			em.merge(p);
	   			em.flush();
	   		} catch (Exception e){
	   			throw new ServiciosException(e.getMessage());
	   		}
	   	}


	   	public void delete(Long id) throws ServiciosException {
	   		try{			
	   			em.remove(getId(id));
	   			em.flush();
	   		} catch (Exception e){
	   			throw new ServiciosException(e.getMessage());
	   		}
	   	}
	   	

	   	public Producto getId(Long id) throws ServiciosException {
	   		try{
	   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.getId", Producto.class)
	   					.setParameter("id", id);
	   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
	   		}catch (Exception e) {
	   			throw new ServiciosException(e.getMessage());
	   		}

	   	}
	   	

	   	public Producto getNombre(String nombre) throws ServiciosException {
	   		try{
	   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.getNombre", Producto.class)
	   					.setParameter("nombre", nombre);
	   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
	   		}catch (Exception e) {
	   			throw new ServiciosException(e.getMessage());
	   		}
	   		
	   	}

	   	

	   	public LinkedList<Producto> getNombreLike(String nombre) throws ServiciosException {
	   		LinkedList<Producto> ProductosList = new LinkedList<>();
	   		try {
	   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.getNombreLike", Producto.class)
	   					.setParameter("nombre", "%"+nombre.toUpperCase()+"%");;
	   			ProductosList.addAll(query.getResultList());
	   		} catch (Exception e) {
	   			throw new ServiciosException(e.getMessage());
	   		}
	   		return ProductosList;
	   	}
	   	

	   	public LinkedList<Producto> getAll() throws ServiciosException {
	   		LinkedList<Producto> ProductosList = new LinkedList<>();
	   		try {
	   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.getAll", Producto.class);
	   			ProductosList.addAll(query.getResultList());
	   		} catch (Exception e) {
	   			throw new ServiciosException(e.getMessage());
	   		}
	   		return ProductosList;
	   	}



		public Boolean validoBajaProductos(Producto producto) throws ServiciosException {
			
	   		try{
	   			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.getIdProd", Movimiento.class)
	   					.setParameter("producto", producto);
	   			return (query.getResultList().size()==0) ? false :  true;
	   		}catch (Exception e) {
	   			throw new ServiciosException(e.getMessage());
	   		}
	   	}

		public Producto getProducto(Long id) throws ServiciosException {
			try{		
				Producto producto = em.find(Producto.class, id); 
				return producto;
			}catch(PersistenceException e){
				throw new ServiciosException("No se pudo encontrar el almacenamiento");
			}
		}
		

		public List<Producto> getAllProductos() throws ServiciosException {
			try{		
				TypedQuery<Producto> query = em.createQuery("Producto.getAll",Producto.class); 
				return query.getResultList();
			}catch(PersistenceException e){
				throw new ServiciosException("No se pudo obtener lista de productos");
			}
		}
		
	////// que usa obtenerProductos()
		
		/*public List<Producto> obtenerProductos() {
			try {
				return em.createQuery("Producto.getAll", Producto.class).getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}*/
		
		
}
