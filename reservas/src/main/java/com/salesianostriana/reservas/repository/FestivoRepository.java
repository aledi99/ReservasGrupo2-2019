package com.salesianostriana.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.reservas.model.Festivo;

public interface FestivoRepository extends JpaRepository<Festivo, Long> {

}
