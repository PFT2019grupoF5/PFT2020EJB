package com.services;
//
import java.util.List;
import javax.ejb.Remote;

import com.entities.Almacenamiento;
import com.entities.Movimiento;
import com.exception.ServiciosException;

@Remote
public interface MovimientoBeanRemote {

	void add(Movimiento movimiento) throws ServiciosException;

	void update(Movimiento movimiento) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	Movimiento getId(Long id) throws ServiciosException;

	int getMovimientoxAlmac(Long idAlma) throws ServiciosException; 
	
	Movimiento getMovimiento(Long id) throws ServiciosException;

	List<Movimiento> getAllMovimientos() throws ServiciosException;

	Movimiento validoBajaProducto(Long idProducto) throws ServiciosException;

}
