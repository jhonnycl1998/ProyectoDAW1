package com.examenT1.turismo.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenT1.turismo.bean.dto.ClienteDTO;
import com.examenT1.turismo.usecase.ClienteUseCase;

import lombok.RequiredArgsConstructor;

@RequestMapping("/clientes")
@RestController
@RequiredArgsConstructor
public class ClienteController implements Serializable{
	
		private static final long serialVersionUID = 1L;
	
		private final ClienteUseCase clienteUseCase;
		
	    @PostMapping
	    public ResponseEntity<ClienteDTO> registrar(@RequestBody ClienteDTO dto) {
	        ClienteDTO creado = clienteUseCase.registrarCliente(dto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	    }
		
	    @GetMapping
	    public ResponseEntity<List<ClienteDTO>> listar(){
	    	return ResponseEntity.ok(clienteUseCase.listarClientes());
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Integer id){
	    	ClienteDTO cliente = clienteUseCase.buscarClientePorId(id);
	    	if(cliente == null) {
	    		return ResponseEntity.notFound().build();
	    	}
	    	return ResponseEntity.ok(cliente);
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<ClienteDTO> actualizar(@PathVariable Integer id, @RequestBody ClienteDTO dto){
	    	ClienteDTO actualizado = clienteUseCase.actualizarCliente(id, dto);
	    	if(actualizado == null) {
	    		return ResponseEntity.notFound().build();
	    	}
	    	return ResponseEntity.ok(actualizado);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
	    	boolean eliminado = clienteUseCase.eliminarCliente(id);
	    	
	    	if(!eliminado) {
	    		return ResponseEntity.notFound().build();
	    	}
	    	
	    	return ResponseEntity.noContent().build();
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    

}
