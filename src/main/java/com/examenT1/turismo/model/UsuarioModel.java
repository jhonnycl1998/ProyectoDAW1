package com.examenT1.turismo.model;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examenT1.turismo.bean.dto.LoginDTO;
import com.examenT1.turismo.bean.dto.UsuarioDTO;
import com.examenT1.turismo.bean.entity.Usuario;
import com.examenT1.turismo.persistence.UsuarioRepository;
import com.examenT1.turismo.usecase.UsuarioUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioModel implements UsuarioUseCase{
	private final UsuarioRepository usuarioRepository;
	
	
	@Override
	public List<UsuarioDTO> listarUsuarios() {
	    return usuarioRepository.findAll()
	            .stream()
	            .map(this::convertirADTO)
	            .toList();
	}

	@Override
	public UsuarioDTO buscarUsuarioPorId(Integer idUsuario) {
		return usuarioRepository.findById(idUsuario)
		        .map(this::convertirADTO)
		        .orElse(null);
	}

	@Override
	public boolean eliminarUsuario(Integer idUsuario) {
		if (!usuarioRepository.existsById(idUsuario)) {
            return false;
        }

        usuarioRepository.deleteById(idUsuario);
        return true;
	}

	@Override
	public UsuarioDTO login(LoginDTO loginDTO) {
		Usuario usuario = usuarioRepository
				.findByUsuarioAndContrasena(loginDTO.getUsuario(), loginDTO.getContrasena())
				.orElse(null);
		if(usuario == null) return null;
		
		return UsuarioDTO.builder()
				.idUsuario(usuario.getIdUsuario())
				.usuario(usuario.getUsuario())
				.idTrabajador(
						usuario.getTrabajador() != null ? usuario.getTrabajador().getIdTrabajador():null
						)
				.build();
	}
	
	private UsuarioDTO convertirADTO(Usuario usuario) {
	    return UsuarioDTO.builder()
	            .idUsuario(usuario.getIdUsuario())
	            .usuario(usuario.getUsuario())
	            .idTrabajador(
	                    usuario.getTrabajador() != null
	                            ? usuario.getTrabajador().getIdTrabajador()
	                            : null
	            )
	            .build();
	}
    
    

}
