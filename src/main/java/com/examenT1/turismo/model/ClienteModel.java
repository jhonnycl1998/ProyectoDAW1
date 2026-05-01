package com.examenT1.turismo.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examenT1.turismo.bean.dto.ClienteDTO;
import com.examenT1.turismo.bean.entity.Cliente;
import com.examenT1.turismo.persistence.ClienteRepository;
import com.examenT1.turismo.usecase.ClienteUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClienteModel implements ClienteUseCase{
	
	private final ClienteRepository clienteRepository;

    @Override
    public ClienteDTO registrarCliente(ClienteDTO clienteDTO) {

        Cliente cliente = Cliente.builder()
                .nombre(clienteDTO.getNombre())
                .apellido(clienteDTO.getApellido())
                .edad(clienteDTO.getEdad())
                .dni(clienteDTO.getDni())
                .direccion(clienteDTO.getDireccion())
                .build();

        Cliente guardado = clienteRepository.save(cliente);

        return convertirADTO(guardado);
    }

    @Override
    public List<ClienteDTO> listarClientes() {
    	return clienteRepository.findAll()
    	        .stream()
    	        .map(this::convertirADTO)
    	        .toList();
    }

    @Override
    public ClienteDTO buscarClientePorId(Integer idCliente) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElse(null);

        if (cliente == null) return null;

        return convertirADTO(cliente);
    }

    @Override
    public ClienteDTO actualizarCliente(Integer idCliente, ClienteDTO clienteDTO) {

        Cliente cliente = clienteRepository.findById(idCliente)
                .orElse(null);

        if (cliente == null) return null;

        cliente.setNombre(clienteDTO.getNombre());
        cliente.setApellido(clienteDTO.getApellido());
        cliente.setEdad(clienteDTO.getEdad());
        cliente.setDni(clienteDTO.getDni());
        cliente.setDireccion(clienteDTO.getDireccion());

        Cliente actualizado = clienteRepository.save(cliente);

        return convertirADTO(actualizado);
    }

    @Override
    public boolean eliminarCliente(Integer idCliente) {

        if (!clienteRepository.existsById(idCliente)) {
            return false;
        }

        clienteRepository.deleteById(idCliente);
        return true;
    }
    
    private ClienteDTO convertirADTO(Cliente cliente) {
        return ClienteDTO.builder()
                .idCliente(cliente.getIdCliente())
                .nombre(cliente.getNombre())
                .apellido(cliente.getApellido())
                .edad(cliente.getEdad())
                .dni(cliente.getDni())
                .direccion(cliente.getDireccion())
                .build();
    }
	

}
