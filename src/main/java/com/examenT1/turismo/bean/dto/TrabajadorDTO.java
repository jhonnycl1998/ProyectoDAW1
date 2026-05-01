package com.examenT1.turismo.bean.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrabajadorDTO {

	private Integer idTrabajador;
	private String nombre;
	private String apellido;
	private Integer edad;
	private String dni_ruc;
	private String direccion;
	private Integer idUsuario;
    private String usuario;
    private String contrasena;
	
}
