package com.salesianostriana.reservas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.model.Reserva;
import com.salesianostriana.reservas.model.Usuario;
/**
 * 
 * @author Esperanza M Escacena M
 *
 */
public interface ReservaRepository  extends JpaRepository<Reserva, Long>{
	Reserva findByFechaAndHoraAndAula(LocalDate fecha,Horas hora,Aula aula);
	
	List<Reserva> findByFechaAndAula(LocalDate fecha, Aula aula);
	
	Reserva findByUsuarioAndFechaAndHora(Usuario usuario, LocalDate fecha, Horas hora);
	
	List<Reserva> findByAula(Aula aula);
	
	List<Reserva> findByUsuario(Usuario usuario);
}
