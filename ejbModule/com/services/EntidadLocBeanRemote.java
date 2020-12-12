package com.services;

import java.util.List;
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

	EntidadLoc getEntidadLoc(Long id) throws ServiciosException;

	List<EntidadLoc> getAllEntidadesLoc() throws ServiciosException;



}
