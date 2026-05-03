package com.examenT1.turismo.model;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.examenT1.turismo.bean.dto.TourDTO;
import com.examenT1.turismo.bean.entity.Tour;
import com.examenT1.turismo.persistence.TourRepository;
import com.examenT1.turismo.usecase.TourUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TourModel implements TourUseCase{

	private final TourRepository tourRepository;

    @Override
    public TourDTO registrarTour(TourDTO tourDTO) {

        Tour tour = Tour.builder()
                .nombre(tourDTO.getNombre())
                .descripcion(tourDTO.getDescripcion())
                .fechaTour(tourDTO.getFechaTour())
                .horaInicio(tourDTO.getHoraInicio())
                .capacidadTotal(tourDTO.getCapacidadTotal())
                .asientosDisponibles(tourDTO.getCapacidadTotal())
                .precio(tourDTO.getPrecio())
                .condiciones(tourDTO.getCondiciones())
                .build();

        Tour guardado = tourRepository.save(tour);

        return convertirADTO(guardado);
    }

    @Override
    public List<TourDTO> listarTours() {
        return tourRepository.findAll(Sort.by("idTour"))
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public List<TourDTO> listarToursDisponibles() {
        return tourRepository.findAll()
                .stream()
                .filter(tour -> tour.getAsientosDisponibles() > 0)
                .map(this::convertirADTO)
                .toList();
    }

    @Override
    public TourDTO buscarTourPorId(Integer idTour) {
        Tour tour = tourRepository.findById(idTour).orElse(null);

        if (tour == null) return null;

        return convertirADTO(tour);
    }

    @Override
    public TourDTO actualizarTour(Integer idTour, TourDTO tourDTO) {
        Tour tour = tourRepository.findById(idTour).orElse(null);

        if (tour == null) return null;

        tour.setNombre(tourDTO.getNombre());
        tour.setDescripcion(tourDTO.getDescripcion());
        tour.setFechaTour(tourDTO.getFechaTour());
        tour.setHoraInicio(tourDTO.getHoraInicio());
        tour.setCapacidadTotal(tourDTO.getCapacidadTotal());
        tour.setAsientosDisponibles(tourDTO.getAsientosDisponibles());
        tour.setPrecio(tourDTO.getPrecio());
        tour.setCondiciones(tourDTO.getCondiciones());

        Tour actualizado = tourRepository.save(tour);

        return convertirADTO(actualizado);
    }

    @Override
    public boolean eliminarTour(Integer idTour) {
        if (!tourRepository.existsById(idTour)) {
            return false;
        }

        tourRepository.deleteById(idTour);
        return true;
    }

    private TourDTO convertirADTO(Tour tour) {
        return TourDTO.builder()
                .idTour(tour.getIdTour())
                .nombre(tour.getNombre())
                .descripcion(tour.getDescripcion())
                .fechaTour(tour.getFechaTour())
                .horaInicio(tour.getHoraInicio())
                .capacidadTotal(tour.getCapacidadTotal())
                .asientosDisponibles(tour.getAsientosDisponibles())
                .precio(tour.getPrecio())
                .condiciones(tour.getCondiciones())
                .build();
    }

}
