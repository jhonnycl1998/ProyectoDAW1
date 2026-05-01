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

import com.examenT1.turismo.bean.dto.TourDTO;
import com.examenT1.turismo.usecase.TourUseCase;

import lombok.RequiredArgsConstructor;


@RequestMapping("/tours")
@RestController
@RequiredArgsConstructor
public class TourController implements Serializable{
	
		private static final long serialVersionUID = 1L;	
	
		private final TourUseCase tourUseCase;
		
	    @PostMapping
	    public ResponseEntity<TourDTO> registrar(@RequestBody TourDTO dto) {
	    	TourDTO creado = tourUseCase.registrarTour(dto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	    }
		
	    @GetMapping
	    public ResponseEntity<List<TourDTO>> listar(){
	    	return ResponseEntity.ok(tourUseCase.listarTours());
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<TourDTO> buscarPorId(@PathVariable Integer id){
	    	TourDTO tour = tourUseCase.buscarTourPorId(id);
	    	if(tour == null) {
	    		return ResponseEntity.notFound().build();
	    	}
	    	return ResponseEntity.ok(tour);
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<TourDTO> actualizar(@PathVariable Integer id, @RequestBody TourDTO dto){
	    	TourDTO actualizado = tourUseCase.actualizarTour(id, dto);
	    	if(actualizado == null) {
	    		return ResponseEntity.notFound().build();
	    	}
	    	return ResponseEntity.ok(actualizado);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> eliminar(@PathVariable Integer id){
	    	boolean eliminado = tourUseCase.eliminarTour(id);
	    	
	    	if(!eliminado) {
	    		return ResponseEntity.notFound().build();
	    	}
	    	
	    	return ResponseEntity.noContent().build();
	    }
	    


}
