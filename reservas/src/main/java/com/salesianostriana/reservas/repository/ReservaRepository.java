package com.salesianostriana.reservas.repository;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.model.Reserva;

public interface ReservaRepository  extends JpaRepository<Reserva, Long>{
	Reserva findByFechaAndHora(LocalDate fecha,Horas hora);
}
