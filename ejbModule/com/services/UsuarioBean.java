package com.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.DAOservices.UsuarioDAO;
import com.entities.Usuario;
import com.enumerated.tipoPerfil;
import com.exception.ServiciosException;
import java.util.List;



/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
public class UsuarioBean implements UsuarioBeanRemote {

    
	@EJB
	private UsuarioDAO usuarioDAO; 
	
    public UsuarioBean() {
        // TODO Auto-generated constructor stub
    }

	
	@Override
	public void add(String nombre, String apellido, String nomAcceso, String contrasena, String correo, tipoPerfil tipoPerfil) throws ServiciosException {
		try{
			Usuario u = new Usuario();
			u.setNombre(nombre);
			u.setApellido(apellido);
			u.setNomAcceso(nomAcceso);
			u.setContrasena(contrasena);
			u.setCorreo(correo);
			u.setTipoPerfil(tipoPerfil);
			usuarioDAO.add(u);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, String nombre, String apellido, String nomAcceso, String contrasena, String correo, tipoPerfil tipoPerfil) throws ServiciosException {
		try{
			Usuario u = getId(id);
			u.setNombre(nombre);
			u.setApellido(apellido);
			u.setNomAcceso(nomAcceso);
			u.setContrasena(contrasena);
			u.setCorreo(correo);
			u.setTipoPerfil(tipoPerfil);
			usuarioDAO.update(id, u);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void cambiarContrasena(String nomAcceso, String contrasena) throws ServiciosException {
		try{
			Usuario u = getNA(nomAcceso);
			u.setContrasena(contrasena);
			usuarioDAO.cambiarContrasena(nomAcceso, contrasena);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try{
			usuarioDAO.delete(id);
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}
	
	@Override
	public Usuario getNA(String nomAcceso) throws ServiciosException {
		try{
			return usuarioDAO.getNA(nomAcceso);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}
	
	@Override
	public Usuario getId(Long id) throws ServiciosException {
		try{
			return usuarioDAO.getId(id);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	
	@Override
	public boolean ValidarContrasena(String nomAcceso, String contrasena) throws ServiciosException {
		boolean ContrasenaOk = false;
		try {
			ContrasenaOk= getNA(nomAcceso).getContrasena().equals(contrasena);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return ContrasenaOk;
	}
	
	@Override
	public Usuario getUsuario(Long id) throws ServiciosException {
		try{
			return usuarioDAO.getUsuario(id);
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el usuario");
		}
	}
	
	@Override
	public List<Usuario> getAllUsuarios() throws ServiciosException {
		try{		
			return usuarioDAO.getAllUsuarios();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de usuarios");
		}
	}



}
