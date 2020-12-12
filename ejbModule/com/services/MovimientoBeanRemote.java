package com.services;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import com.entities.Almacenamiento;
import com.entities.Movimiento;
import com.entities.Producto;
import com.enumerated.tipoMovimiento;
import com.exception.ServiciosException;

@Remote
public interface MovimientoBeanRemote {

	void add(Date fecha, int cantidad, String descripcion, double costo, tipoMovimiento tipoMov, Producto producto,
			 Almacenamiento almacenamiento) throws ServiciosException;

	void update(Long id, Date fecha, int cantidad, String descripcion, double costo, tipoMovimiento tipoMov,
			Producto producto,  Almacenamiento almacenamiento) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	Movimiento getId(Long id) throws ServiciosException;

	Movimiento getMovimiento(Long id) throws ServiciosException;

	List<Movimiento> getAllMovimientos() throws ServiciosException;



}
