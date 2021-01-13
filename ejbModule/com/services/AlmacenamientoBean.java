package com.services;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.DAOservices.AlmacenamientoDAO;
import com.entities.Almacenamiento;
import com.exception.ServiciosException;

@Stateless
public class AlmacenamientoBean implements AlmacenamientoBeanRemote {

	@EJB
	private AlmacenamientoDAO almacenamientoDAO;

	public AlmacenamientoBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void add(Almacenamiento almacenamiento) throws ServiciosException {
		try {

			almacenamientoDAO.add(almacenamiento);
		} catch (Exception e) {
			throw new ServiciosException("Error en add AlmacenamientoBean : " + e.getMessage());
		}
	}

	@Override
	public void update(Almacenamiento almacenamiento) throws ServiciosException {
		try {
			almacenamientoDAO.update(almacenamiento);
		} catch (Exception e) {
			throw new ServiciosException("Error en update AlmacenamientoBean : " + e.getMessage());
		}
	}

	@Override
	public void delete(Long id) throws ServiciosException {
		try {
			almacenamientoDAO.delete(id);
		} catch (Exception e) {
			throw new ServiciosException("Error en delete AlmacenamientoBean : " + e.getMessage());
		}
	}

	@Override
	public Almacenamiento getId(Long id) throws ServiciosException {
		try {
			return almacenamientoDAO.getId(id);
		} catch (Exception e) {
			throw new ServiciosException("Error en getId AlmacenamientoBean : " + e.getMessage());
		}
	}

	@Override
	public Almacenamiento getNombre(String nombre) throws ServiciosException {
		try {
			return almacenamientoDAO.getNombre(nombre);
		} catch (Exception e) {
			throw new ServiciosException(e.getMessage());
		}
	}
	
	@Override
	public int getAlmacenamientoxLoc(long idLoc) throws ServiciosException {
		try{
			return almacenamientoDAO.getAlmacenamientoxLoc(idLoc);
		}catch (Exception e) {
			throw new ServiciosException("Error en getId AlmacenamientoBean : " + e.getMessage());
		}
	}
	
	@Override
	public Almacenamiento getAlmacenamiento(Long id) throws ServiciosException {
		try {
			return almacenamientoDAO.getAlmacenamiento(id);
		} catch (Exception e) {
			throw new ServiciosException("No se pudo encontrar el almacenamiento >> " + e.getMessage());
		}
	}

	@Override
	public List<Almacenamiento> getAllAlmacenamientos() throws ServiciosException {
		try {
			return almacenamientoDAO.getAllAlmacenamientos();
		} catch (Exception e) {
			throw new ServiciosException("No se pudo obtener lista de almacenamientos >> " + e.getMessage());
		}
	}

	
}
