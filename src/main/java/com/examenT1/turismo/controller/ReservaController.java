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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examenT1.turismo.bean.dto.ReservaDTO;
import com.examenT1.turismo.bean.enums.EstadoReserva;
import com.examenT1.turismo.usecase.ReservaUseCase;

import lombok.RequiredArgsConstructor;

@RequestMapping("/reservas")
@RestController
@RequiredArgsConstructor
public class ReservaController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final ReservaUseCase reservaUseCase;
	
	@PostMapping
	public ResponseEntity<ReservaDTO> registrar (@RequestBody ReservaDTO dto){
		ReservaDTO creado = reservaUseCase.registrarReserva(dto);
		
		if(creado == null) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}
	
	@GetMapping
	public ResponseEntity<List<ReservaDTO>> listar(){
		return ResponseEntity.ok(reservaUseCase.listarReservas());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReservaDTO> buscarPorId(@PathVariable Integer id){
		ReservaDTO reserva = reservaUseCase.buscarReservaPorId(id);
		
		if(reserva == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(reserva);
	}
	
	@GetMapping("/cliente/{idCliente}")
	public ResponseEntity<List<ReservaDTO>> listarPorCliente(@PathVariable Integer idCliente){
		return ResponseEntity.ok(reservaUseCase.listarReservasPorCliente(idCliente));
	}
	
	@PutMapping("/{id}/estado")
	public ResponseEntity<ReservaDTO> cambiarEstado(@PathVariable Integer id, @RequestParam EstadoReserva estado){
		ReservaDTO actualizado = reservaUseCase.cambiarEstadoReserva(id, estado);
		if(actualizado == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(actualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		boolean eliminado = reservaUseCase.eliminarReserva(id);
		if(!eliminado) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
}
