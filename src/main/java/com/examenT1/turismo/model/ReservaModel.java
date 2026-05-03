package com.examenT1.turismo.model;
import org.springframework.data.domain.Sort;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import com.examenT1.turismo.bean.dto.ReservaDTO;
import com.examenT1.turismo.bean.entity.Cliente;
import com.examenT1.turismo.bean.entity.Reserva;
import com.examenT1.turismo.bean.entity.Tour;
import com.examenT1.turismo.bean.entity.Trabajador;
import com.examenT1.turismo.bean.enums.EstadoReserva;
import com.examenT1.turismo.persistence.ClienteRepository;
import com.examenT1.turismo.persistence.ReservaRepository;
import com.examenT1.turismo.persistence.TourRepository;
import com.examenT1.turismo.persistence.TrabajadorRepository;
import com.examenT1.turismo.usecase.ReservaUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReservaModel implements ReservaUseCase
{
	private final ReservaRepository reservaRepository;
	private final TourRepository tourRepository;
	private final ClienteRepository clienteRepository;
	private final TrabajadorRepository trabajadorRepository;
	
	@Override
	public ReservaDTO registrarReserva(ReservaDTO reservaDTO) {
		Tour tour = tourRepository.findById(reservaDTO.getIdTour()).orElse(null);
	    Cliente cliente = clienteRepository.findById(reservaDTO.getIdCliente()).orElse(null);
	    Trabajador trabajador = trabajadorRepository.findById(reservaDTO.getIdTrabajador()).orElse(null);

	    if (tour == null || cliente == null || trabajador == null) {
	        return null;
	    }

	    if (tour.getAsientosDisponibles() < reservaDTO.getAsientosReservados()) {
	        return null;
	    }

	    tour.setAsientosDisponibles(
	            tour.getAsientosDisponibles() - reservaDTO.getAsientosReservados()
	    );

	    Reserva reserva = Reserva.builder()
	            .fechaReserva(reservaDTO.getFechaReserva())
	            .asientosReservados(reservaDTO.getAsientosReservados())
	            .estado(EstadoReserva.PENDIENTE)
	            .tour(tour)
	            .cliente(cliente)
	            .trabajador(trabajador)
	            .build();

	    Reserva guardado = reservaRepository.save(reserva);

	    return convertirADTO(guardado);
	}

	@Override
	public List<ReservaDTO> listarReservas() {
		return reservaRepository.findAll(Sort.by("idReserva"))
				.stream()
				.map(this::convertirADTO)
				.toList();
	}

	@Override
	public ReservaDTO buscarReservaPorId(Integer idReserva) {
		Reserva reserva = reservaRepository.findById(idReserva).orElse(null);
		if (reserva == null) return null;
		return convertirADTO(reserva);
	}

	@Override
	public List<ReservaDTO> listarReservasPorCliente(Integer idCliente) {
		return reservaRepository.findAll()
				.stream()
				.filter(reserva -> reserva.getCliente().getIdCliente().equals(idCliente))
				.map(this::convertirADTO)
				.toList();
	}

	@Override
	public boolean eliminarReserva(Integer idReserva) {

	    Reserva reserva = reservaRepository.findById(idReserva).orElse(null);

	    if (reserva == null) {
	        return false;
	    }

	    Tour tour = reserva.getTour();

	    
	    tour.setAsientosDisponibles(
	        tour.getAsientosDisponibles() + reserva.getAsientosReservados()
	    );

	    tourRepository.save(tour);

	    reservaRepository.delete(reserva);

	    return true;
	}

	@Override
	public ReservaDTO cambiarEstadoReserva(Integer idReserva, EstadoReserva estado) {
		Reserva reserva = reservaRepository.findById(idReserva).orElse(null);
		
		if(reserva == null) return null;
		
		reserva.setEstado(estado);
		
		Reserva actualizado = reservaRepository.save(reserva);
		return convertirADTO(actualizado);
	}
	
	private ReservaDTO convertirADTO(Reserva reserva) {

	    return ReservaDTO.builder()
	            .idReserva(reserva.getIdReserva())
	            .fechaReserva(reserva.getFechaReserva())
	            .asientosReservados(reserva.getAsientosReservados())
	            .costoTotal(
	                    reserva.getTour().getPrecio()
	                            .multiply(BigDecimal.valueOf(reserva.getAsientosReservados()))
	            )
	            .estado(reserva.getEstado())
	            .idTour(reserva.getTour().getIdTour())
	            .idCliente(reserva.getCliente().getIdCliente())
	            .idTrabajador(reserva.getTrabajador().getIdTrabajador())
	            .nombreTour(reserva.getTour().getNombre())
	            .nombreCliente(reserva.getCliente().getNombre() + " " + reserva.getCliente().getApellido())
	            .nombreTrabajador(reserva.getTrabajador().getNombre() + " " + reserva.getTrabajador().getApellido())
	            .build();
	}
	
}

