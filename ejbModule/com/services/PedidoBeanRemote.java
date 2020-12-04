package com.services;

import java.util.Date;
import java.util.LinkedList;

import javax.ejb.Remote;

import com.entities.Pedido;
import com.entities.Usuario;
import com.enumerated.estadoPedido;
import com.exception.ServiciosException;

@Remote
public interface PedidoBeanRemote {

	void add(Date pedfecestim, Date fecha, int pedreccodigo, Date pedrecfecha, String pedreccomentario,
			estadoPedido pedestado, Usuario usuario) throws ServiciosException;

	LinkedList<Pedido> getAll() throws ServiciosException;

	Pedido get(Long id) throws ServiciosException;

	void delete(Long id) throws ServiciosException;

	void update(Long id, Date pedfecestim, Date fecha, int pedreccodigo, Date pedrecfecha, String pedreccomentario,
			estadoPedido pedestado, Usuario usuario) throws ServiciosException;

}
