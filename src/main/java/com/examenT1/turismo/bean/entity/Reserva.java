package com.examenT1.turismo.bean.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.examenT1.turismo.bean.enums.EstadoReserva;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="tb_reserva")
@Builder
public class Reserva implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idReserva")
	private Integer idReserva;
	
	@Column(name="fecha")
	private LocalDate fechaReserva;
	
	@Column(name="asientos_reservados")
	private Integer asientosReservados;
	
	@Transient
	private Double costoTotal;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "estado")
	private EstadoReserva estado;
	
	@JoinColumn(name="id_tour")
	@ManyToOne(fetch = FetchType.EAGER)
	private Tour tour;
	
	@JoinColumn(name="idCliente")
	@ManyToOne(fetch = FetchType.EAGER)
	private Cliente cliente;
	
	//Aca verificamos quien hace la reserva
	
	@JoinColumn(name="id_trabajador")
	@ManyToOne(fetch = FetchType.EAGER)
	private Trabajador trabajador;
	
	
	
	
	
	


}
