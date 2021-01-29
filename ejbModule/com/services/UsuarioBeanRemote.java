package com.services;


import java.util.List;
import javax.ejb.Remote;
import com.entities.Usuario;
import com.exception.ServiciosException;

@Remote
public interface UsuarioBeanRemote {

	void add(Usuario usuario) throws ServiciosException;

	void update(Usuario usuario) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	Usuario getNA(String nomAcceso) throws ServiciosException;

	Usuario getId(Long id) throws ServiciosException;

	boolean ValidarContrasena(String nomAcceso, String contrasena) throws ServiciosException;

	Usuario getUsuario(Long id) throws ServiciosException;

	List<Usuario> getAllUsuarios() throws ServiciosException;




}
