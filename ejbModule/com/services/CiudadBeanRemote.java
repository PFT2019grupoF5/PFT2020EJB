package com.services;

import java.util.List;
import javax.ejb.Remote;
import com.entities.Ciudad;
import com.exception.ServiciosException;

@Remote
public interface CiudadBeanRemote {

	void add(Ciudad ciudad) throws ServiciosException;

	void update(Ciudad ciudad) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	Ciudad getNombre(String nombre) throws ServiciosException;

	Ciudad getId(Long id) throws ServiciosException;
	
	List<Ciudad> getAllCiudades() throws ServiciosException;

	Ciudad getCiudad(Long id) throws ServiciosException;

}
