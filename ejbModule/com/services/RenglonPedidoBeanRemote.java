package com.services;

import java.util.List;
import javax.ejb.Remote;
import com.entities.RenglonPedido;
import com.exception.ServiciosException;

@Remote
public interface RenglonPedidoBeanRemote {

	void update(RenglonPedido renglonPedido) throws ServiciosException;

	void add(RenglonPedido renglonPedido) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	RenglonPedido getId(Long id) throws ServiciosException;

	RenglonPedido getRenglonPedido(Long id) throws ServiciosException;

	List<RenglonPedido> getAllRenglonesPedido() throws ServiciosException;

	int getRenglonxPedido(Long idPedido) throws ServiciosException;
	
	int getRenglonxProducto(Long idProducto) throws ServiciosException;

	List<RenglonPedido> getRenglonesDelPedido(Long idPedido) throws ServiciosException;

	RenglonPedido getRen(int rennro) throws ServiciosException;

}
