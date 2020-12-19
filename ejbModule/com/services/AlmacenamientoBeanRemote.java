package com.services;

import java.util.List;
import javax.ejb.Remote;
import com.entities.Almacenamiento;
import com.exception.ServiciosException;
//
@Remote
public interface AlmacenamientoBeanRemote {

	void add(Almacenamiento almacenamiento) throws ServiciosException;

	void update(Almacenamiento almacenamiento) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	Almacenamiento getId(Long id) throws ServiciosException;

	Almacenamiento getNombre(String nombre) throws ServiciosException;

	List<Almacenamiento> getAllAlmacenamientos() throws ServiciosException;

	Almacenamiento getAlmacenamiento(Long id) throws ServiciosException;

}
