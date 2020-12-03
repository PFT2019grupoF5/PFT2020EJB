package com.services;

import java.util.LinkedList;

import javax.ejb.Remote;

import com.entities.Usuario;
import com.enumerated.tipoPerfil;
import com.exception.ServiciosException;

@Remote
public interface UsuarioBeanRemote {

	void add(String nombre, String apellido, String nomAcceso, String contrasena, String correo, tipoPerfil tipoPerfil)
			throws ServiciosException;

	void update(String nomAcceso, String contrasena) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	Usuario getNA(String nomAcceso) throws ServiciosException;

	Usuario getId(Long id) throws ServiciosException;

	LinkedList<Usuario> getAll() throws ServiciosException;

	boolean ValidarContrasena(String nomAcceso, String contrasena) throws ServiciosException;

	Object[][] getNombreApellido(String nombre, String apellido) throws ServiciosException;



}
