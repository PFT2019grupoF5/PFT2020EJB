package com.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import com.entities.Usuario;
import com.enumerated.tipoPerfil;
import com.exception.ServiciosException;
import java.util.LinkedList;
import java.util.List;



/**
 * Session Bean implementation class UsuarioBean
 */
@Stateless
public class UsuarioBean implements UsuarioBeanRemote {

    /**
     * Default constructor. 
     */
    public UsuarioBean() {
        // TODO Auto-generated constructor stub
    }

	@PersistenceContext
	private EntityManager em;
	
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
			em.persist(u);
			em.flush();
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
			em.merge(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void cambiarContrasena(String nomAcceso, String contrasena) throws ServiciosException {
		try{
			Usuario u = getNA(nomAcceso);
			u.setContrasena(contrasena);
			em.merge(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try{			
			em.remove(getId(id));
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}
	
	@Override
	public Usuario getNA(String nomAcceso) throws ServiciosException {
		try{
			TypedQuery<Usuario> query =  em.createNamedQuery("Usuario.getNA", Usuario.class)
					.setParameter("nomAcceso", nomAcceso);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}
	
	@Override
	public Usuario getId(Long id) throws ServiciosException {
		try{
			TypedQuery<Usuario> query =  em.createNamedQuery("Usuario.getId", Usuario.class)
					.setParameter("id", id);
			return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		
	}
	
	
	
	@Override
	public LinkedList<Usuario> getAll() throws ServiciosException {
		LinkedList<Usuario> usuariosList = new LinkedList<>();
		try {
			TypedQuery<Usuario> query =  em.createNamedQuery("Usuario.getAll", Usuario.class);
			usuariosList.addAll(query.getResultList());
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return usuariosList;
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
			Usuario usuario = em.find(Usuario.class, id);
			return usuario;
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo encontrar el usuario");
		}
	}
	
	@Override
	public List<Usuario> getAllUsuarios() throws ServiciosException {
		try{		
			TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u",Usuario.class); 
			return query.getResultList();
		}catch(PersistenceException e){
			throw new ServiciosException("No se pudo obtener lista de usuarios");
		}
	}



}
