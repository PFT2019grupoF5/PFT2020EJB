package com.DAOservices;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Usuario;
import com.exception.ServiciosException;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;


@Stateless
@LocalBean
public class UsuarioDAO {

	 public UsuarioDAO() {
	        // TODO Auto-generated constructor stub
	    }

		@PersistenceContext
		private EntityManager em;
		

		public void add(Usuario usuario) throws ServiciosException {
			try{
				em.persist(usuario);
				em.flush();
			} catch (Exception e){
				throw new ServiciosException(e.getMessage());
			}
		}


		public void update(Long id, Usuario usuario) throws ServiciosException {
			try{
				Usuario u = getId(id);
				u.setNombre(usuario.getNombre());
				u.setApellido(usuario.getApellido());
				u.setNomAcceso(usuario.getNomAcceso());
				u.setContrasena(usuario.getContrasena());
				u.setCorreo(usuario.getCorreo());
				u.setTipoPerfil(usuario.getTipoPerfil());
				em.merge(u);
				em.flush();
			} catch (Exception e){
				throw new ServiciosException(e.getMessage());
			}
		}


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


		public void delete(Long id) throws ServiciosException {
			try{			
				em.remove(getId(id));
				em.flush();
			} catch (Exception e){
				throw new ServiciosException(e.getMessage());
			}
		}
		

		public Usuario getNA(String nomAcceso) throws ServiciosException {
			try{
				TypedQuery<Usuario> query =  em.createNamedQuery("Usuario.getNA", Usuario.class)
						.setParameter("nomAcceso", nomAcceso);
				return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
			}catch (Exception e) {
				throw new ServiciosException(e.getMessage());
			}
		}
		

		public Usuario getId(Long id) throws ServiciosException {
			try{
				TypedQuery<Usuario> query =  em.createNamedQuery("Usuario.getId", Usuario.class)
						.setParameter("id", id);
				return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
			}catch (Exception e) {
				throw new ServiciosException(e.getMessage());
			}
			
		}
		

		public boolean ValidarContrasena(String nomAcceso, String contrasena) throws ServiciosException {
			boolean ContrasenaOk = false;
			try {
				ContrasenaOk= getNA(nomAcceso).getContrasena().equals(contrasena);
			} catch (Exception e) {
				throw new ServiciosException(e.getMessage());
			}
			return ContrasenaOk;
		}
		

		public Usuario getUsuario(Long id) throws ServiciosException {
			try{
				Usuario usuario = em.find(Usuario.class, id);
				return usuario;
			}catch(PersistenceException e){
				throw new ServiciosException("No se pudo encontrar el usuario");
			}
		}
		

		public List<Usuario> getAllUsuarios() throws ServiciosException {
			try{		
				TypedQuery<Usuario> query = em.createQuery("Usuario.getAll",Usuario.class); 
				return query.getResultList();
			}catch(PersistenceException e){
				throw new ServiciosException("No se pudo obtener lista de usuarios");
			}
		}
	
}
