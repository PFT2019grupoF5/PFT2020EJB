package com.services;

import java.util.List;
import javax.ejb.Remote;
import com.entities.Producto;
import com.exception.ServiciosException;


@Remote
public interface ProductoBeanRemote {
	
	void add(Producto producto) throws ServiciosException;
	
	void update(Producto producto) throws ServiciosException;
	
	void delete(Long id) throws ServiciosException;

	Producto getId(Long id) throws ServiciosException;

	Producto getNombre(String nombre) throws ServiciosException;
	
	Boolean validoBajaProductos(Producto producto) throws ServiciosException;

	Producto getProducto(Long id) throws ServiciosException;

	List<Producto> getAllProductos() throws ServiciosException;

}
