package com.examenT1.turismo.usecase;

import java.util.List;

import com.examenT1.turismo.bean.dto.TrabajadorDTO;

public interface TrabajadorUseCase {

    TrabajadorDTO registrarTrabajador(TrabajadorDTO trabajadorDTO);

    List<TrabajadorDTO> listarTrabajadores();

    TrabajadorDTO buscarTrabajadorPorId(Integer idTrabajador);

    boolean eliminarTrabajador(Integer idTrabajador);

}