package com.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	public void update(String nomAcceso, String contrasena) throws ServiciosException {
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
			TypedQuery<Usuario> query =  em.createNamedQuery("Usuario.getId", Usuario.class)
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
	public Object[][] getNombreApellido(String nombre, String apellido) throws ServiciosException {
		TypedQuery<Usuario> query;
		Object [][] datos = null;
		List<Usuario> list = null;
		try {
			query =  em.createNamedQuery("Usuario.getNombreApellido",Usuario.class)
					.setParameter("nombre", "%"+nombre.toUpperCase()+"%").setParameter("apellido", "%"+apellido.toUpperCase()+"%");
			list = query.getResultList();
			datos = new Object[list.size()][6];
			int i = 0;
			for(Usuario u :list){
				datos[i] = new Object[]{u.getId(),u.getNombre(),u.getApellido(),u.getNomAcceso(),u.getCorreo(),u.getTipoPerfil()};
				i++;
			}
			
		}catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return datos;
	}
}
