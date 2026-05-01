package com.examenT1.turismo.usecase;

import java.util.List;

import com.examenT1.turismo.bean.dto.TourDTO;

public interface TourUseCase {
	TourDTO registrarTour(TourDTO tourDTO);

    List<TourDTO> listarTours();

    List<TourDTO> listarToursDisponibles();

    TourDTO buscarTourPorId(Integer idTour);

    TourDTO actualizarTour(Integer idTour, TourDTO tourDTO);

    boolean eliminarTour(Integer idTour);

}
