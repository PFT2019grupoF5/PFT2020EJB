package com.services;

import java.util.LinkedList;

import javax.ejb.Remote;

import com.entities.Ciudad;
import com.exception.ServiciosException;

@Remote
public interface CiudadBeanRemote {

	void add(String nombre) throws ServiciosException;

	void update(String nombre) throws ServiciosException;

	void delete(String nombre) throws ServiciosException;

	Ciudad get(String nombre) throws ServiciosException;

	LinkedList<Ciudad> getAll() throws ServiciosException;

}
