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

	void update(Long id, int volumen, String nombre, double costoop, double capestiba, double cappeso,
			EntidadLoc entidadLoc) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	Almacenamiento getId(Long id) throws ServiciosException;

	LinkedList<Almacenamiento> getNombreLike(String nombre) throws ServiciosException;

	LinkedList<Almacenamiento> getAll() throws ServiciosException;

}
