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

import com.examenT1.turismo.bean.dto.LoginDTO;
import com.examenT1.turismo.bean.dto.UsuarioDTO;
import com.examenT1.turismo.usecase.UsuarioUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private final UsuarioUseCase usuarioUseCase;
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> listar(){
		return ResponseEntity.ok(usuarioUseCase.listarUsuarios());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable Integer id){
		UsuarioDTO usuario = usuarioUseCase.buscarUsuarioPorId(id);
		
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(usuario);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UsuarioDTO> login(@RequestBody LoginDTO loginDTO){
		UsuarioDTO usuario = usuarioUseCase.login(loginDTO);
		
		if(usuario == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.ok(usuario);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id){
		boolean eliminado = usuarioUseCase.eliminarUsuario(id);
		if(!eliminado) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
