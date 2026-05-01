package com.examenT1.turismo.bean.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name="tb_tour")
public class Tour implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_tour")
	private Integer idTour;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="descripcion")
	private String descripcion;
	
	@Column(name="fecha_tour")
	private LocalDate fechaTour;
	
	@Column(name="hora_inicio")
	private LocalTime horaInicio;
	
	@Column(name="capacidad_total")
	private Integer capacidadTotal;
	
	@Column(name="asientos_disponibles")
	private Integer asientosDisponibles;
		
	@Column(name="precio")
	private BigDecimal precio;
	
	@Column(name="condiciones")
	private String condiciones;
	
	
	@OneToMany(mappedBy = "tour", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Reserva> reservas;

}
