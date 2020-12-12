package com.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.DAOservices.CiudadDAO;
import com.entities.Ciudad;
import com.exception.ServiciosException;
import java.util.List;

/**
 * Session Bean implementation class CiudadBean
 */
@Stateless
public class CiudadBean implements CiudadBeanRemote {

	@EJB
	private CiudadDAO ciudadDAO;

	public CiudadBean() {
	}

	@Override
	public void add(String nombre) throws ServiciosException {
		try {
			Ciudad c = new Ciudad();
			c.setNombre(nombre);
			ciudadDAO.add(c);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, String nombre) throws ServiciosException {
		try {
			Ciudad c = getId(id);
			c.setNombre(nombre);
			ciudadDAO.update(id, c);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try {
			ciudadDAO.delete(id);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public Ciudad getId(Long id) throws ServiciosException {
		try {

			return ciudadDAO.getCiudad(id);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	@Override
	public Ciudad getNombre(String nombre) throws ServiciosException {
		try {
			return ciudadDAO.getNombre(nombre);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	@Override
	public Ciudad getCiudad(Long id) throws ServiciosException {
		try {

			return ciudadDAO.getId(id);
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo encontrar la ciudad");
		}
	}

	@Override
	public List<Ciudad> getAllCiudades() throws ServiciosException {
		try {

			return ciudadDAO.getAllCiudades();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se puede obtener lista de ciudades");
		}

	}

}
