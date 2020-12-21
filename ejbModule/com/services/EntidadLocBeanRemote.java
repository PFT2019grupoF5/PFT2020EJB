package com.services;

import java.util.List;
import javax.ejb.Remote;
import com.entities.EntidadLoc;
import com.exception.ServiciosException;

@Remote
public interface EntidadLocBeanRemote {

	void add(EntidadLoc entidadLoc) throws ServiciosException;

	void update(EntidadLoc entidadLoc) throws ServiciosException;

	EntidadLoc getCodigo(int codigo) throws ServiciosException;
	
	EntidadLoc getId(Long id) throws ServiciosException;

	int getLocalesxCiu(long idCiu) throws ServiciosException; 

	void delete(Long id) throws ServiciosException;

	EntidadLoc getEntidadLoc(Long id) throws ServiciosException;

	List<EntidadLoc> getAllEntidadesLoc() throws ServiciosException;

	EntidadLoc getNombre(String nombre) throws ServiciosException;

}
