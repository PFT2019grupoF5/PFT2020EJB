package com.services;

import java.util.LinkedList;

import javax.ejb.Remote;

import com.entities.Familia;
import com.exception.ServiciosException;

@Remote
public interface FamiliaBeanRemote {

	void add(String nombre, String descrip, String incompat) throws ServiciosException;

	void update(String nombre, String descrip, String incompat) throws ServiciosException;

	void delete(String nombre) throws ServiciosException;

	Familia get(String nombre) throws ServiciosException;

	LinkedList<Familia> getAll() throws ServiciosException;

}
