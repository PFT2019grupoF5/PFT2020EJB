package com.services;

import java.util.LinkedList;

import javax.ejb.Remote;

import com.entities.Ciudad;
import com.exception.ServiciosException;

@Remote
public interface CiudadBeanRemote {

	void add(String nombre) throws ServiciosException;

	void update(Long id) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	Ciudad getNombre(String nombre) throws ServiciosException;

	Ciudad getId(Long id) throws ServiciosException;
	
	LinkedList<Ciudad> getNombreLike(String nombre) throws ServiciosException;

	LinkedList<Ciudad> getAll() throws ServiciosException;


}
