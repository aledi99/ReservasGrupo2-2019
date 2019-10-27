package com.salesianostriana.reservas.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.model.Reserva;
import com.salesianostriana.reservas.repository.AulaRepository;
import com.salesianostriana.reservas.repository.ReservaRepository;


//@DataJpaTest
class ReservaRespositoryTest {
	@Autowired
	private ReservaRepository rr;

//	@Test
//	public void comprobarRepositorioIsNotNull() {
//		assertThat(rr).isNotNull();
//	}
	
	/*@Sql("data2.sql")
	@Test
	public void buscarPorFechaHoraYAula() {
		Reserva reserva=rr.findByFechaAndHoraAndAula(LocalDate.of(2019,10, 27), Horas.PRIMERA, ar.findById(5L).orElse(null));
		assertThat(reserva!=null).isTrue();
	}*/
}
