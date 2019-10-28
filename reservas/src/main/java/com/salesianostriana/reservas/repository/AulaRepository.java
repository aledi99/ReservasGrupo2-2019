package com.salesianostriana.reservas.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.reservas.model.Aula;

public interface AulaRepository extends JpaRepository<Aula, Long> {

	public Page<Aula> findAll(Pageable pageable);	
}
