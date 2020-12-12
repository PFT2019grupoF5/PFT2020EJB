package com.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.DAOservices.FamiliaDAO;
import com.entities.Familia;
import com.exception.ServiciosException;
import java.util.List;

@Stateless
public class FamiliaBean implements FamiliaBeanRemote {

	@EJB
	private FamiliaDAO familiaDAO;

	public FamiliaBean() {
	}

	@Override
	public void add(Familia familia) throws ServiciosException {
		try {
			familiaDAO.add(familia);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Familia familia) throws ServiciosException {
		try {
			familiaDAO.update(familia);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try {
			familiaDAO.delete(id);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public Familia getNombre(String nombre) throws ServiciosException {
		try {
			return familiaDAO.getNombre(nombre);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	@Override
	public Familia getId(Long id) throws ServiciosException {
		try {
			return familiaDAO.getFamilia(id);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}

	}

	@Override
	public Familia getFamilia(Long id) throws ServiciosException {
		try {
			return familiaDAO.getFamilia(id);
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo encontrar la familia");
		}
	}

	@Override
	public List<Familia> getAllFamilias() throws ServiciosException {
		try {
			return familiaDAO.getAllFamilias();
		} catch (PersistenceException e) {
			throw new ServiciosException("No se pudo obtener lista de familias");
		}
	}

}
