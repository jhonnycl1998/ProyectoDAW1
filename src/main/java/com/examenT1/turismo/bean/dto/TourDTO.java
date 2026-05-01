package com.examenT1.turismo.bean.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourDTO {
	
	private Integer idTour;
	private String nombre;
	private String descripcion;
	private LocalDate fechaTour;
	private LocalTime horaInicio;
	private Integer capacidadTotal;
	private Integer asientosDisponibles;
	private BigDecimal precio;
	private String condiciones;

}
