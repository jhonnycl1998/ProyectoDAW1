package com.examenT1.turismo.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.examenT1.turismo.bean.dto.LoginDTO;
import com.examenT1.turismo.bean.dto.UsuarioDTO;
@Component
public interface UsuarioUseCase {

    List<UsuarioDTO> listarUsuarios();

    UsuarioDTO buscarUsuarioPorId(Integer idUsuario);

    boolean eliminarUsuario(Integer idUsuario);

    UsuarioDTO login(LoginDTO loginDTO);

}
