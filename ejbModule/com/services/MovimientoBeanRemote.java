package com.services;

import java.util.Date;
import java.util.LinkedList;

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

	LinkedList<Movimiento> getAll() throws ServiciosException;

	LinkedList<Movimiento> getMovLike(String descripcion) throws ServiciosException;



}
