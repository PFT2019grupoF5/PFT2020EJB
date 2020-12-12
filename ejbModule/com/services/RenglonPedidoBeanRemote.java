package com.services;

import java.util.List;
import javax.ejb.Remote;
import com.entities.Pedido;
import com.entities.Producto;
import com.entities.RenglonPedido;
import com.exception.ServiciosException;

@Remote
public interface RenglonPedidoBeanRemote {

	void update(Long id, int rennro, int rencant, Producto producto, Pedido pedido) throws ServiciosException;

	void add(int rennro, int rencant, Producto producto, Pedido pedido) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	RenglonPedido getId(Long id) throws ServiciosException;

	RenglonPedido getRenglonPedido(Long id) throws ServiciosException;

	List<RenglonPedido> getAllRenglonesPedido() throws ServiciosException;

}
