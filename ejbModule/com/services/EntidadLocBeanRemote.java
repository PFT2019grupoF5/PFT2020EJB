package com.services;

import java.util.LinkedList;

import javax.ejb.Remote;

import com.entities.Ciudad;
import com.entities.EntidadLoc;
import com.enumerated.tipoLoc;
import com.exception.ServiciosException;

@Remote
public interface EntidadLocBeanRemote {

	void add(int codigo, String nombre, String direccion, tipoLoc tipoLoc, Ciudad ciudad) throws ServiciosException;

	void update(Long id, int codigo, String nombre, String direccion, tipoLoc tipoLoc, Ciudad ciudad)
			throws ServiciosException;

	EntidadLoc getCodigo(int codigo) throws ServiciosException;
	
	EntidadLoc getId(Long id) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	LinkedList<EntidadLoc> getCodigoLike(int codigo) throws ServiciosException;

	LinkedList<EntidadLoc> getAll() throws ServiciosException;



}
