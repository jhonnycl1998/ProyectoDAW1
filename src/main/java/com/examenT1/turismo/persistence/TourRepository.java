package com.examenT1.turismo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examenT1.turismo.bean.entity.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Integer>{

}
