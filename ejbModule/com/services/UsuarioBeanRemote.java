package com.services;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Remote;

import com.entities.Usuario;
import com.enumerated.tipoPerfil;
import com.exception.ServiciosException;

@Remote
public interface UsuarioBeanRemote {

	void add(String nombre, String apellido, String nomAcceso, String contrasena, String correo, tipoPerfil tipoPerfil)
			throws ServiciosException;

	void update(String nomAcceso, String contrasena) throws ServiciosException;

	void delete(String u) throws ServiciosException;

	Usuario get(String nomAcceso) throws ServiciosException;

	LinkedList<Usuario> getAll() throws ServiciosException;

	boolean ValidarContrasena(String nomAcceso, String contrasena) throws ServiciosException;

	Object[][] getAllByApellidoOrNomAcceso(String apellido, String nomAcceso) throws ServiciosException;

	List<Usuario> getAllByApellidoOrNomAccesoAsList(String apellido, String nomAcceso) throws ServiciosException;
	

}
