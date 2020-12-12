package com.services;

import java.util.List;
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

	Familia getFamilia(Long id) throws ServiciosException;

	List<Familia> getAllFamilias() throws ServiciosException;

}
