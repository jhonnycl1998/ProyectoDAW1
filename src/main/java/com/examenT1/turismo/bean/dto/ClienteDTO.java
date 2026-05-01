package com.examenT1.turismo.bean.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ClienteDTO {
	private Integer idCliente;
	private String nombre;
	private String apellido;
	private Integer edad;
	private String dni;
	private String direccion;	

}
