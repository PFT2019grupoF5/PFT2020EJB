package com.services;

import java.util.Date;
import java.util.LinkedList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.entities.Producto;
import com.entities.Familia;
import com.entities.Usuario;
import com.enumerated.Segmentacion;
import com.exception.ServiciosException;

/**
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
   			Producto u = new Producto();
   			u.setNombre(nombre);
   			u.setLote(lote);
   			u.setPrecio(precio);
   			u.setFelab(felab);
   			u.setFven(fven);
   			u.setPeso(peso);
   			u.setVolumen(volumen);
   			u.setEstiba(estiba);
   			u.setStkMin(stkMin);
   			u.setStkTotal(stkTotal);
   			u.setSegmentac(segmentac);
   			u.setUsuario(usuario);
   			u.setFamilia(familia);
   			em.persist(u);
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public void update(String nombre, String lote, double precio, Date felab, Date fven, double peso, double volumen, int estiba, double stkMin, double stkTotal, Segmentacion segmentac, Usuario usuario, Familia familia) throws ServiciosException {
   		try{
   			Producto u = get(nombre);
   			u.setLote(lote);
   			u.setPrecio(precio);
   			u.setFelab(felab);
   			u.setFven(fven);
   			u.setPeso(peso);
   			u.setVolumen(volumen);
   			u.setEstiba(estiba);
   			u.setStkMin(stkMin);
   			u.setStkTotal(stkTotal);
   			u.setSegmentac(segmentac);
   			u.setUsuario(usuario);
   			u.setFamilia(familia);
   			em.merge(u);
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public void delete(String nombre) throws ServiciosException {
   		try{			
   			em.remove(get(nombre));
   			em.flush();
   		} catch (Exception e){
   			throw new ServiciosException(e.getMessage());
   		}
   	}

   	@Override
   	public Producto get(String nombre) throws ServiciosException {
   		try{
   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.get", Producto.class)
   					.setParameter("nombre", nombre);
   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
   		}catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		
   	}
   	@Override
   	public Producto getId(Long id) throws ServiciosException {
   		try{
   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.get", Producto.class)
   					.setParameter("id", id);
   			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
   		}catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		
   	}
   	
   	@Override
   	public LinkedList<Producto> getAll() throws ServiciosException {
   		LinkedList<Producto> ProductoList = new LinkedList<>();
   		try {
   			TypedQuery<Producto> query =  em.createNamedQuery("Producto.getAll", Producto.class);
   			ProductoList.addAll(query.getResultList());
   		} catch (Exception e) {
   			throw new ServiciosException(e.getMessage());
   		}
   		return ProductoList;
   	}

}
