package com.DAOservices;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import com.entities.Usuario;
import com.exception.ServiciosException;
import javax.ejb.Stateless;


@Stateless
public class UsuarioDAO {

		@PersistenceContext
		private EntityManager em;
		
		public UsuarioDAO() {
		        // TODO Auto-generated constructor stub
		}


		public void add(Usuario usuario) throws ServiciosException {
			try{
				em.persist(usuario);
				em.flush();
			} catch (Exception e){
				throw new ServiciosException("Error al crear Usuario : " + e.getMessage());
			}
		}


		public void update(Usuario usuario) throws ServiciosException {
			try{
				em.merge(usuario);
				em.flush();
			} catch (Exception e){
				throw new ServiciosException("Error al modificar Usuario : " + e.getMessage());
			}
		}


		public void delete(Long id) throws ServiciosException {
			try{			
				Usuario usuario = em.find(Usuario.class,  id);
				em.remove(usuario);
				em.flush();
			} catch (Exception e){
				throw new ServiciosException("Error al borrar Usuario : " + e.getMessage());
			}
		}
		

		public Usuario getNA(String nomAcceso) throws ServiciosException {
			try{
				TypedQuery<Usuario> query =  em.createNamedQuery("Usuario.getNA", Usuario.class)
						.setParameter("nomAcceso", nomAcceso);
				return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
			}catch (Exception e) {
				throw new ServiciosException("Error al traer por nomAcceso Usuario : " + e.getMessage());
			}
		}
		

		public Usuario getId(Long id) throws ServiciosException {
			try{
				TypedQuery<Usuario> query =  em.createNamedQuery("Usuario.getId", Usuario.class)
						.setParameter("id", id);
				return (query.getResultList().size()==0) ? null :  query.getResultList().get(0);
			}catch (Exception e) {
				throw new ServiciosException("Error al traer por Id Usuario : " + e.getMessage());
			}
			
		}
		

		public boolean ValidarContrasena(String nomAcceso, String contrasena) throws ServiciosException {
			boolean ContrasenaOk = false;
			try {
				ContrasenaOk= getNA(nomAcceso).getContrasena().equals(contrasena);
			} catch (Exception e) {
				throw new ServiciosException("Error al ValidarContrasena : " + e.getMessage());
			}
			return ContrasenaOk;
		}
		

		public Usuario getUsuario(Long id) throws ServiciosException {
			try{
				Usuario usuario = em.find(Usuario.class, id);
				return usuario;
			}catch(Exception e){
				throw new ServiciosException("No se pudo encontrar el usuario : " + e.getMessage());
			}
		}
		

		public List<Usuario> getAllUsuarios() throws ServiciosException {
			try{		
				TypedQuery<Usuario> query = em.createNamedQuery("Usuario.getAll",Usuario.class); 
				return query.getResultList();
			}catch(Exception e){
				throw new ServiciosException("No se pudo obtener lista de usuarios : " + e.getMessage());
			}
		}
	
}
