package com.examenT1.turismo.usecase;

import java.util.List;

import com.examenT1.turismo.bean.dto.ClienteDTO;


public interface ClienteUseCase {
	
	ClienteDTO registrarCliente(ClienteDTO clienteDTO);

    List<ClienteDTO> listarClientes();

    ClienteDTO buscarClientePorId(Integer idCliente);

    boolean eliminarCliente(Integer idCliente);

    ClienteDTO actualizarCliente(Integer idCliente, ClienteDTO clienteDTO);

}
