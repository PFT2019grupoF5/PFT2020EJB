package com.services;

import java.util.Date;
import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

   	
   	
}
