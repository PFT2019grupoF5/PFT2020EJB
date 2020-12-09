package com.services;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Remote;

import com.entities.Familia;
import com.entities.Producto;
import com.entities.Usuario;
import com.enumerated.Segmentacion;
import com.exception.ServiciosException;


@Remote
public interface ProductoBeanRemote {
	
	//Comentario para Luis

	void add(String nombre, String lote, double precio, Date felab, Date fven, double peso, double volumen, int estiba,
			double stkMin, double stkTotal, Segmentacion segmentac, Usuario usuario, Familia familia)
			throws ServiciosException;
	
	void update(Long id, String nombre, String lote, double precio, Date felab, Date fven, double peso, double volumen,
			int estiba, double stkMin, double stkTotal, Segmentacion segmentac, Usuario usuario, Familia familia)
			throws ServiciosException;
	
	void delete(Long id) throws ServiciosException;

	Producto getId(Long id) throws ServiciosException;

	Producto getNombre(String nombre) throws ServiciosException;
	
	LinkedList<Producto> getNombreLike(String nombre) throws ServiciosException;

	LinkedList<Producto> getAll() throws ServiciosException;
	
	Boolean validoBajaProductos(Producto producto) throws ServiciosException;

	Producto getProducto(Long id) throws ServiciosException;

	List<Producto> getAllProductos() throws ServiciosException;

	List<Producto> obtenerProductos();

	void modificarProducto(Producto producto) throws ServiciosException;


}
