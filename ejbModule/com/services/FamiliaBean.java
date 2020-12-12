package com.services;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import com.DAOservices.FamiliaDAO;
import com.entities.Familia;
import com.exception.ServiciosException;
import java.util.List;

@Stateless
@LocalBean
public class FamiliaBean implements FamiliaBeanRemote {

	@EJB
	private FamiliaDAO familiaDAO;

	public FamiliaBean() {

	}

	@Override
	public void add(String nombre, String descrip, String incompat) throws ServiciosException {
		try {
			Familia f = new Familia();
			f.setNombre(nombre);
			f.setDescrip(descrip);
			f.setIncompat(incompat);
			familiaDAO.add(f);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}

	@Override
	public void update(Long id, String nombre, String descrip, String incompat) throws ServiciosException {
		try {
			Familia f = getId(id);
			f.setNombre(nombre);
			f.setDescrip(descrip);
			f.setIncompat(incompat);
			familiaDAO.update(id, f);
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
			return familiaDAO.getId(id);
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
