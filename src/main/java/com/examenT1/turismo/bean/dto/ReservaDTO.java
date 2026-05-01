package com.examenT1.turismo.bean.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.examenT1.turismo.bean.enums.EstadoReserva;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservaDTO {
	private Integer idReserva;
	private LocalDate fechaReserva;
	private Integer asientosReservados;
	private BigDecimal costoTotal;
	private EstadoReserva estado;
    private Integer idTour;
    private Integer idCliente;
    private Integer idTrabajador;
    private String nombreTour;
    private String nombreCliente;
    private String nombreTrabajador;

}
