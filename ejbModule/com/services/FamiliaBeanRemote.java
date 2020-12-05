package com.services;

import java.util.LinkedList;

import javax.ejb.Remote;

import com.entities.Familia;
import com.exception.ServiciosException;

@Remote
public interface FamiliaBeanRemote {

	void add(String nombre, String descrip, String incompat) throws ServiciosException;

	

	void update(Long id, String nombre, String descrip, String incompat) throws ServiciosException;


	void delete(Long id) throws ServiciosException;


	Familia getNombre(String nombre) throws ServiciosException;


	Familia getId(Long id) throws ServiciosException;


	LinkedList<Familia> getNombreLike(String nombre) throws ServiciosException;

	LinkedList<Familia> getAll() throws ServiciosException;

	
}
