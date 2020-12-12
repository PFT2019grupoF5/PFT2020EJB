package com.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.DAOservices.CiudadDAO;
import com.entities.Ciudad;
import com.exception.ServiciosException;
import java.util.List;

@Stateless
public class CiudadBean implements CiudadBeanRemote {

	@EJB
	private CiudadDAO ciudadDAO;

	public CiudadBean() {
	}

	@Override
	public void add(Ciudad ciudad) throws ServiciosException {
		try {
			ciudadDAO.add(ciudad);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Ciudad ciudad) throws ServiciosException {
		try {
			ciudadDAO.update(ciudad);
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
			return ciudadDAO.getCiudad(id);
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
