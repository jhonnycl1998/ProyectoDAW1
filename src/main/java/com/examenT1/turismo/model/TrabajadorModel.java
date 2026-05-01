package com.examenT1.turismo.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examenT1.turismo.bean.dto.TrabajadorDTO;
import com.examenT1.turismo.bean.entity.Trabajador;
import com.examenT1.turismo.bean.entity.Usuario;
import com.examenT1.turismo.persistence.TrabajadorRepository;
import com.examenT1.turismo.usecase.TrabajadorUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TrabajadorModel implements TrabajadorUseCase{
	
	private final TrabajadorRepository trabajadorRepository;

	@Override
	public TrabajadorDTO registrarTrabajador(TrabajadorDTO trabajadorDTO) {
		 Usuario usuario = Usuario.builder()
	                .usuario(trabajadorDTO.getUsuario())
	                .contrasena(trabajadorDTO.getContrasena())
	                .build();

	        Trabajador trabajador = Trabajador.builder()
	                .nombre(trabajadorDTO.getNombre())
	                .apellido(trabajadorDTO.getApellido())
	                .edad(trabajadorDTO.getEdad())
	                .dni_ruc(trabajadorDTO.getDni_ruc())
	                .direccion(trabajadorDTO.getDireccion())
	                .usuario(usuario)
	                .build();

	        usuario.setTrabajador(trabajador);

	        Trabajador guardado = trabajadorRepository.save(trabajador);

	        return TrabajadorDTO.builder()
	                .idTrabajador(guardado.getIdTrabajador())
	                .nombre(guardado.getNombre())
	                .apellido(guardado.getApellido())
	                .edad(guardado.getEdad())
	                .dni_ruc(guardado.getDni_ruc())
	                .direccion(guardado.getDireccion())
	                .idUsuario(guardado.getUsuario().getIdUsuario())
	                .build();
	}

	@Override
	public List<TrabajadorDTO> listarTrabajadores() {
		 return trabajadorRepository.findAll()
	                .stream()
	                .map(trabajador -> TrabajadorDTO.builder()
	                        .idTrabajador(trabajador.getIdTrabajador())
	                        .nombre(trabajador.getNombre())
	                        .apellido(trabajador.getApellido())
	                        .edad(trabajador.getEdad())
	                        .dni_ruc(trabajador.getDni_ruc())
	                        .direccion(trabajador.getDireccion())
	                        .idUsuario(
	                                trabajador.getUsuario() != null
	                                        ? trabajador.getUsuario().getIdUsuario()
	                                        : null
	                        )
	                        .build())
	                .toList();
	}

	@Override
	public TrabajadorDTO buscarTrabajadorPorId(Integer idTrabajador) {
		Trabajador trabajador = trabajadorRepository.findById(idTrabajador)
                .orElse(null);

        if (trabajador == null) return null;

        return TrabajadorDTO.builder()
                .idTrabajador(trabajador.getIdTrabajador())
                .nombre(trabajador.getNombre())
                .apellido(trabajador.getApellido())
                .edad(trabajador.getEdad())
                .dni_ruc(trabajador.getDni_ruc())
                .direccion(trabajador.getDireccion())
                .idUsuario(
                        trabajador.getUsuario() != null
                                ? trabajador.getUsuario().getIdUsuario()
                                : null
                )
                .build();
	}

	@Override
	public boolean eliminarTrabajador(Integer idTrabajador) {
		if(!trabajadorRepository.existsById(idTrabajador)) {
			return false;
		}
		trabajadorRepository.deleteById(idTrabajador);
		return true;
	}
	
	

}
