package com.salesianostriana.reservas.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.model.Reserva;
import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.repository.ReservaRepository;
import com.salesianostriana.reservas.service.ReservaServicio;

/**
 * 
 * @author Esperanza M Escacena M
 *
 *
 */
@SpringBootTest
public class ReservaServicioTest {
	@InjectMocks
	private ReservaServicio rs;
	
	@Mock
	private ReservaRepository rr;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	private LocalDate date=LocalDate.of(2019, 10, 24);
	private List<LocalDate> weekOfDate= Arrays.asList(
			LocalDate.of(2019, 10, 21),
			LocalDate.of(2019, 10, 22),
			LocalDate.of(2019, 10, 23),
			date,
			LocalDate.of(2019, 10, 25),
			LocalDate.of(2019, 10, 26),
			LocalDate.of(2019, 10, 27));
	
	private Usuario usuario1=new Usuario("prueba1@prueba.com","1111","Prueba1","Mock1",false);
	//private Usuario usuario2=new Usuario("prueba2@prueba.com","2222","Prueba2","Mock2",false);
	
	
	@Test
	public void testCalcularSemanaMes() {
		List<LocalDate> expected=rs.CalcularSemanasMes(date);
		
		assertEquals(expected, weekOfDate, "El test para proporcionar un List de las fechas de una semana no es correcto");
	}
	
	@Test
	public void testCalcularSemanaMesValorLimite() {
		LocalDate date=LocalDate.of(2019, 10, 31);
		
		List<LocalDate> weekOfDate= Arrays.asList(
				LocalDate.of(2019, 10, 28),
				LocalDate.of(2019, 10, 29),
				LocalDate.of(2019, 10, 30),
				LocalDate.of(2019, 10, 31),
				null,
				null,
				null);
		
		List<LocalDate> expected=rs.CalcularSemanasMes(date);
		assertEquals(expected, weekOfDate, "El test para proporcionar un List de las fechas de una semana no es correcto");
		
	}
	
	@Test
	public void testListarReservasCalendario() {
		Aula aula=new Aula();
		aula.setId(1L);
		aula.setNombre("2dam");
		usuario1.setGestionado(true);
		usuario1.setId(2L);
		Reserva mockReserva=new Reserva (Horas.PRIMERA,aula,usuario1,date);
		when(rr.findByFechaAndHoraAndAula(date, Horas.PRIMERA, aula)).thenReturn(mockReserva);
		List<Reserva> mockList=Arrays.asList(null,null,null,mockReserva,null,null,null);
		List<Reserva> expected=rs.listarReservasCalendario(Horas.PRIMERA, weekOfDate, aula);
		assertEquals(expected, mockList, "El test para proporcionar un List de reservas de un aula, en una fecha y hora concreta no funciona correctamente");

	}
	
//	@Test
//	public void testListarHorasLibres() {
//		
//		
//	}
//	
//	@Test
//	public void testComprobarReservaImposible() {
//		
//	}
//	
//	@Test
//	public void testBuscarReservasFuturas() {
//		
//	}
//	
//	@Test
//	public void testBuscarReservasPasadas() {
//		
//	}
//	
	@Test
	public void testEliminarReservaPorAula() {
	//preguntar como
	}
	
	@Test
	public void testListarReservasPorUsuario() {
		List<Reserva> mockList=Arrays.asList(
				new Reserva(Horas.CUARTA, new Aula(), usuario1, date),
				new Reserva(Horas.PRIMERA, new Aula(), usuario1, date)
				);
		when(rr.findByUsuario(usuario1)).thenReturn(mockList);
		List<Reserva> expected=rr.findByUsuario(usuario1);
		assertEquals(expected, mockList, "El test para proporcionar un List de reservas de un usuario dado no funciona correctamente");


	}
	/*
	 * PREGUNTAR SI HACE FALTA
	@Test
	public void testListarReservasPorUsuarioNulo() {
		List<Reserva> mockList=null;
		when(rr.findByUsuario(null)).thenReturn(mockList);
		List<Reserva> expected=rr.findByUsuario(usuario1);
		assertEquals(expected, mockList, "El test para proporcionar un List de reservas de un usuario dado no funciona correctamente");

	}*/
	
}
