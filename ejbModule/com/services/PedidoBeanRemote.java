package com.services;

import java.util.List;
import javax.ejb.Remote;
import com.entities.Pedido;
import com.entities.RenglonReporte;
import com.exception.ServiciosException;

@Remote
public interface PedidoBeanRemote {

	void add(Pedido pedido) throws ServiciosException;

	Pedido getId(Long id) throws ServiciosException;
	
	void delete(Long id) throws ServiciosException;

	void update(Pedido pedido) throws ServiciosException;

	Pedido getPedido(Long id) throws ServiciosException;

	List<Pedido> getAllPedidos() throws ServiciosException;

	List<Pedido> getPedidosEntreFechas(String fechaDesde, String fechaHasta) throws ServiciosException;

	List<RenglonReporte> getReporteEntreFechas(String fechaDesde, String fechaHasta) throws ServiciosException;

}
