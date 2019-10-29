package com.salesianostriana.reservas.repository.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.model.Reserva;
import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.repository.AulaRepository;
import com.salesianostriana.reservas.repository.ReservaRepository;
import com.salesianostriana.reservas.repository.UsuarioRepository;

@DataJpaTest
class ReservaRespositoryTest {
	@Autowired
	private ReservaRepository rr;
	
	@Autowired
	private AulaRepository ar;
	
	@Autowired
	private UsuarioRepository ur;

	@Test
	public void comprobarRepositorioIsNotNull() {
		assertThat(rr).isNotNull();
	}
	
	
	@Test
	//@Sql({"sql/data2.sql"}) no funciona, error de sintaxis
	public void buscarPorFechaHoraYAula() {

		Reserva reserva=rr.findByFechaAndHoraAndAula(LocalDate.of(2019,10, 27), Horas.PRIMERA, ar.findById(33L).orElse(null));
		assertThat(reserva!=null).isTrue();
	}
	
	@Test
	public void buscarPorFechaHoraYAulaNull() {

		Reserva reserva=rr.findByFechaAndHoraAndAula(null, null, null);
		assertThat(reserva!=null).isFalse();
	}
	
	@Test
	public void buscarPorFechaHoraYAulaNoExiste() {

		Reserva reserva=rr.findByFechaAndHoraAndAula(LocalDate.of(2019,10, 27), Horas.PRIMERA, ar.findById(500L).orElse(null));
		assertThat(reserva!=null).isFalse();
	}
	@Test
	public void buscarPorFechaNulaHoraYAula() {

		Reserva reserva=rr.findByFechaAndHoraAndAula(null, Horas.PRIMERA, ar.findById(500L).orElse(null));
		assertThat(reserva!=null).isFalse();
		assertDoesNotThrow(()->rr.findByFechaAndHoraAndAula(null, Horas.PRIMERA, ar.findById(500L).orElse(null)));
	}
	
	@Test
	public void buscarPorFechaYAula() {
		List<Reserva> reservas=rr.findByFechaAndAula(LocalDate.of(2019,10, 27), ar.findById(33L).orElse(null));
		assertThat(reservas.isEmpty()).isFalse();
	}
	
	@Test
	public void buscarPorFechaYAulaNula() {
		List<Reserva> reservas=rr.findByFechaAndAula(LocalDate.of(2019,10, 27), null);
		assertThat(reservas.isEmpty()).isTrue();
	}
	@Test
	public void buscarPorFechaNulaYAula() {
		List<Reserva> reservas=rr.findByFechaAndAula(null, ar.findById(33L).orElse(null));
		assertThat(reservas.isEmpty()).isTrue();
	}
	
	@Test
	public void buscarPorUsuarioYFechaYHora() {
		Reserva reserva=rr.findByUsuarioAndFechaAndHora(ur.findById(2L).orElse(null),LocalDate.of(2019,10, 27), Horas.PRIMERA);
		assertThat(reserva==null).isFalse();
	}
	
	@Test
	public void buscarPorUsuarioNoExisteYFechaYHora() {
		Reserva reserva=rr.findByUsuarioAndFechaAndHora(ur.findById(200L).orElse(null),LocalDate.of(2019,10, 27), Horas.PRIMERA);
		assertThat(reserva==null).isTrue();
	}
	
	@Test
	public void buscarPorUsuarioNuloYFechaYHora() {
		Reserva reserva=rr.findByUsuarioAndFechaAndHora(null,LocalDate.of(2019,10, 27), Horas.PRIMERA);
		assertThat(reserva==null).isTrue();
	}
	@Test
	public void buscarPorUsuarioVacioYFechaYHora() {
		assertThrows(InvalidDataAccessApiUsageException.class, ()->rr.findByUsuarioAndFechaAndHora(new Usuario(),LocalDate.of(2019,10, 27), Horas.PRIMERA));
	}
	
	@Test
	public void buscarPorAula() {
		List<Reserva> reservas=rr.findByAula( ar.findById(33L).orElse(null));
		assertThat(reservas.isEmpty()).isFalse();
	}
	
	@Test
	public void buscarPorAulaNula() {
		assertThat(rr.findByAula(null).isEmpty()).isTrue();
	}
	
	@Test
	public void buscarPorUsuario() {
		List<Reserva> reservas=rr.findByUsuario(ur.findById(2L).orElse(null));
		assertThat(reservas.isEmpty()).isFalse();
	}
	
	
}
