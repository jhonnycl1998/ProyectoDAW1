package com.examenT1.turismo.usecase;

import java.util.List;

import org.springframework.stereotype.Component;

import com.examenT1.turismo.bean.dto.ReservaDTO;
import com.examenT1.turismo.bean.enums.EstadoReserva;

@Component
public interface ReservaUseCase {
	
	ReservaDTO registrarReserva(ReservaDTO reservaDTO);

    List<ReservaDTO> listarReservas();

    ReservaDTO buscarReservaPorId(Integer idReserva);

    List<ReservaDTO> listarReservasPorCliente(Integer idCliente);

    boolean eliminarReserva(Integer idReserva);

    ReservaDTO cambiarEstadoReserva(Integer idReserva, EstadoReserva estado);
}
