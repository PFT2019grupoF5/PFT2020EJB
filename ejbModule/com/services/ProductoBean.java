package com.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Producto;
import com.entities.Familia;
import com.entities.Movimiento;
import com.entities.Usuario;
import com.enumerated.Segmentacion;
import com.exception.ServiciosException;

/*
 * Session Bean implementation class ProductoBean
 */
@Stateless
public class ProductoBean implements ProductoBeanRemote {
	
	//Comentario para Luis

    /**
     * Default constructor. 
     */
    public ProductoBean() {
        // TODO Auto-generated constructor stub
    }
    @PersistenceContext
   	private EntityManager em;
   	
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
   			em.persist(p);
   			em.flush();
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
   			em.merge(p);
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public void delete(Long id) throws ServiciosException {
   		try{			
   			em.remove(getId(id));
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}
   	
   	@Override
   	public Producto getId(Long id) throws ServiciosException {
   		try{
   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.getId", Producto.class)
   					.setParameter("id", id);
   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
   		}catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		

   		
   	}
   	
   	@Override
   	public Producto getNombre(String nombre) throws ServiciosException {
   		try{
   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.getNombre", Producto.class)
   					.setParameter("nombre", nombre);
   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
   		}catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		
   	}

   	
   	@Override
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
   	
   	@Override
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


   	@Override
	public Boolean validoBajaProductos(Producto producto) throws ServiciosException {
		
   		try{
   			TypedQuery<Movimiento> query =  em.createNamedQuery("Movimiento.getIdProd", Movimiento.class)
   					.setParameter("producto", producto);
   			return (query.getResultList().size()==0) ? false :  true;
   		}catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   	}
   	@Override
	public Producto getProducto(Long id) throws ServiciosException {
		try{		
			Producto producto = em.find(Producto.class, id); 
			return producto;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el almacenamiento");
		}
	}
	
	@Override
	public List<Producto> getAllProductos() throws ServiciosException {
		try{		
			TypedQuery<Producto> query = em.createQuery("SELECT p FROM Producto p",Producto.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de productos");
		}
	}
	
	//Edit
	
	public List<Producto> obtenerProductos() {
		try {

			return em.createQuery("SELECT a FROM Producto a", Producto.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void modificarProducto(Producto producto) throws ServiciosException {

		try {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// Date Dfelab = sdf.parse(producto.getFelab());
			// Date Dfven = sdf.parse(fven);

			Long id = producto.getId();
			String nombre = producto.getNombre();
			String lote = producto.getLote();
			Date felab = producto.getFelab();
			Date fven = producto.getFven();
			Double precio = producto.getPrecio();
			Double peso = producto.getPeso();
			Double volumen = producto.getVolumen();
			int estiba = producto.getEstiba();
			Double stkMin = producto.getStkMin();
			Double stkTotal = producto.getStkTotal();
			Segmentacion segmentac = producto.getSegmentac();
			Usuario usuario = producto.getUsuario();
			Familia familia = producto.getFamilia();

			Producto produc = em.createQuery("SELECT p from Producto p WHERE p.id = :id", Producto.class)
					.setParameter("id", id).getSingleResult();
			if (produc != null) {
				produc.setNombre(nombre);
				produc.setLote(lote);
				produc.setFelab(felab);
				produc.setFven(fven);
				produc.setPrecio(precio);
				produc.setPeso(peso);
				produc.setVolumen(volumen);
				produc.setEstiba(estiba);
				produc.setStkMin(stkMin);
				produc.setStkTotal(stkTotal);
				produc.setSegmentac(segmentac);
				produc.setUsuario(usuario);
				produc.setFamilia(familia);
				
				this.em.flush();
			}
		} catch (PersistenceException e) {

			throw new ServiciosException("No se puede modificar el producto");

		}

	}
	//edit

	
   	
   	
}
