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

	void update(int codigo, String nombre, String direccion, tipoLoc tipoLoc, Ciudad ciudad) throws ServiciosException;

	EntidadLoc get(int codigo) throws ServiciosException;

	void delete(int codigo) throws ServiciosException;

	LinkedList<EntidadLoc> getAll() throws ServiciosException;

}
