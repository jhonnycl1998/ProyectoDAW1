package com.examenT1.turismo.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examenT1.turismo.bean.dto.TrabajadorDTO;
import com.examenT1.turismo.usecase.TrabajadorUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/trabajadores")
@RequiredArgsConstructor
public class TrabajadorController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final TrabajadorUseCase trabajadorUseCase;
	
	@PostMapping
	public ResponseEntity<TrabajadorDTO> registrar (@RequestBody TrabajadorDTO dto){
		TrabajadorDTO creado = trabajadorUseCase.registrarTrabajador(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}
	
	@GetMapping
	public ResponseEntity<List<TrabajadorDTO>> listar() {
	    return ResponseEntity.ok(trabajadorUseCase.listarTrabajadores());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TrabajadorDTO> buscarPorId(@PathVariable Integer id){
		TrabajadorDTO trabajador = trabajadorUseCase.buscarTrabajadorPorId(id);
		
		if(trabajador == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(trabajador);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		boolean eliminado = trabajadorUseCase.eliminarTrabajador(id);
		
		if(!eliminado) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.noContent().build();
	}
	
	
	

}
