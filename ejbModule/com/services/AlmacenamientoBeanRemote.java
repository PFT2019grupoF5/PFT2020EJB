package com.services;

import java.util.LinkedList;

import javax.ejb.Remote;

import com.entities.Almacenamiento;
import com.entities.EntidadLoc;
import com.exception.ServiciosException;

@Remote
public interface AlmacenamientoBeanRemote {

	void add(int volumen, String nombre, double costoop, double capestiba, double cappeso, EntidadLoc entidadLoc)
			throws ServiciosException;

	void update(int volumen, String nombre, double costoop, double capestiba, double cappeso, EntidadLoc entidadLoc)
			throws ServiciosException;

	void delete(String nombre) throws ServiciosException;

	Almacenamiento get(String nombre) throws ServiciosException;

	LinkedList<Almacenamiento> getAll() throws ServiciosException;

}
