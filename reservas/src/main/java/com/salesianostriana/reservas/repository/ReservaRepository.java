package com.salesianostriana.reservas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.model.Reserva;

public interface ReservaRepository  extends JpaRepository<Reserva, Long>{
	Reserva findByFechaAndHoraAndAula(LocalDate fecha,Horas hora,Aula aula);
	
	List<Reserva> findByFechaAndAulaOrderByHoraAsc(LocalDate fecha, Aula aula);
}
