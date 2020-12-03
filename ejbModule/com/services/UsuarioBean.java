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
			Usuario u = get(nomAcceso);
			u.setContrasena(contrasena);
			em.merge(u);
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(String nomAcceso) throws ServiciosException {
		try{			
			em.remove(get(nomAcceso));
			em.flush();
		} catch (Exception e){
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public Usuario get(String nomAcceso) throws ServiciosException {
		try{
			TypedQuery<Usuario> query =  em.createNamedQuery("Usuario.get", Usuario.class)
					.setParameter("nomAcceso", nomAcceso);
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
	//public boolean checkPwd(String userTyped, String typedPwd) throws serviciosException {
	public boolean ValidarContrasena(String nomAcceso, String contrasena) throws ServiciosException {
		boolean ContrasenaOk = false;
		try {
			ContrasenaOk= get(nomAcceso).getContrasena().equals(contrasena);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return ContrasenaOk;
	}

	@Override
	//public Object[][] getAllByApellidoOrUsuario(String apellido, String usuario) throws serviciosException {
	public Object[][] getAllByApellidoOrNomAcceso(String apellido, String nomAcceso) throws ServiciosException {
		TypedQuery<Usuario> query;
		Object [][] data = null;
		List<Usuario> list = null;
		try {
			if(apellido.isEmpty()){
				query =  em.createNamedQuery("Usuario.getUsuarioLike",Usuario.class)
						.setParameter("nomAcceso", "%"+nomAcceso.toUpperCase()+"%");
			}else{
				query =  em.createNamedQuery("Usuario.getByApellidoLike",Usuario.class)
						.setParameter("apellido", "%"+apellido.toUpperCase()+"%");
			}
			
			list = query.getResultList();
			data = new Object[list.size()][4];
			int i = 0;
			for(Usuario u : list){
				data[i] = new Object[]{u.getNombre(),u.getApellido(),u.getNomAcceso(),u.getCorreo(),u.getTipoPerfil().toString()};
				i++;
			}
			
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return data;
	}
	
	@Override
	//public List<Usuario> getAllByApellidoOrUsuarioAsList(String apellido, String usuario) throws serviciosException {
	public List<Usuario> getAllByApellidoOrNomAccesoAsList(String apellido, String nomAcceso) throws ServiciosException {
		TypedQuery<Usuario> query;
		try {
			if(apellido==null || apellido.isEmpty()){
				query =  em.createNamedQuery("Usuario.getUsuarioLike",Usuario.class)
						.setParameter("nomAcceso", "%"+nomAcceso.toUpperCase()+"%");
			}else{
				query =  em.createNamedQuery("Usuario.getByApellidoLike",Usuario.class)
						.setParameter("apellido", "%"+apellido.toUpperCase()+"%");
			}
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
		return query.getResultList();
	}

    
}
