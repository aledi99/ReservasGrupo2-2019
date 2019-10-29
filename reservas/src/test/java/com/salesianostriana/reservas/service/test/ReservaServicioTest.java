package com.salesianostriana.reservas.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
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
	
	@BeforeEach
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
	public void testCalcularSemanaMesValorLimiteLunes() {
		LocalDate date=LocalDate.of(2019, 9, 30);
		
		List<LocalDate> weekOfDate= Arrays.asList(
				date,
				null,
				null,
				null,
				null,
				null,
				null);
		
		List<LocalDate> expected=rs.CalcularSemanasMes(date);
		
		assertEquals(expected, weekOfDate, "El test para proporcionar un List de las fechas de una semana no es correcto");
		
	}
	
	@Test
	public void testCalcularSemanaMesValorLimiteMartes() {
		LocalDate date=LocalDate.of(2019, 12, 31);
		
		List<LocalDate> weekOfDate= Arrays.asList(
				LocalDate.of(2019, 12, 30),
				date,
				null,
				null,
				null,
				null,
				null);
		
		List<LocalDate> expected=rs.CalcularSemanasMes(date);
		
		assertEquals(expected, weekOfDate, "El test para proporcionar un List de las fechas de una semana no es correcto");
		
	}
	
	@Test
	public void testCalcularSemanaMesValorLimiteMiercoles() {
		LocalDate date=LocalDate.of(2019, 7, 31);
		
		List<LocalDate> weekOfDate= Arrays.asList(
				LocalDate.of(2019, 7, 29),
				LocalDate.of(2019, 7, 30),
				date,
				null,
				null,
				null,
				null);
		
		List<LocalDate> expected=rs.CalcularSemanasMes(date);
		
		assertEquals(expected, weekOfDate, "El test para proporcionar un List de las fechas de una semana no es correcto");
		
	}
	
	@Test
	public void testCalcularSemanaMesValorLimiteJueves() {
		LocalDate date=LocalDate.of(2019, 10, 31);
		
		List<LocalDate> weekOfDate= Arrays.asList(
				LocalDate.of(2019, 10, 28),
				LocalDate.of(2019, 10, 29),
				LocalDate.of(2019, 10, 30),
				date,
				null,
				null,
				null);
		
		List<LocalDate> expected=rs.CalcularSemanasMes(date);
		assertEquals(expected, weekOfDate, "El test para proporcionar un List de las fechas de una semana no es correcto");
		
	}
	
	@Test
	public void testCalcularSemanaMesValorLimiteViernes() {
		LocalDate date=LocalDate.of(2019, 5, 31);
		
		List<LocalDate> weekOfDate= Arrays.asList(
				LocalDate.of(2019, 5, 27),
				LocalDate.of(2019, 5, 28),
				LocalDate.of(2019, 5, 29),
				LocalDate.of(2019, 5, 30),
				date,
				null,
				null);
		
		List<LocalDate> expected=rs.CalcularSemanasMes(date);
		
		assertEquals(expected, weekOfDate, "El test para proporcionar un List de las fechas de una semana no es correcto");
		
	}
	
	@Test
	public void testCalcularSemanaMesValorLimiteSabado() {
		LocalDate date=LocalDate.of(2019,11, 30);
		
		List<LocalDate> weekOfDate= Arrays.asList(
				LocalDate.of(2019,11, 25),
				LocalDate.of(2019,11, 26),
				LocalDate.of(2019,11, 27),
				LocalDate.of(2019,11, 28),
				LocalDate.of(2019,11, 29),
				date,
				null);
		
		List<LocalDate> expected=rs.CalcularSemanasMes(date);
		
		assertEquals(expected, weekOfDate, "El test para proporcionar un List de las fechas de una semana no es correcto");
		
	}
	
	@Test
	public void testCalcularSemanaMesFechaNull() {
		
		assertThrows(NullPointerException.class, ()->rs.CalcularSemanasMes(null));
	}
	
	
	@Test
	public void testListarReservasCalendario() {
		Aula aula=new Aula();
		Reserva mockReserva=new Reserva (Horas.PRIMERA,aula,usuario1,date);
		
		when(rr.findByFechaAndHoraAndAula(date, Horas.PRIMERA, aula)).thenReturn(mockReserva);
		
		List<Reserva> mockList=Arrays.asList(null,null,null,mockReserva,null,null,null);
		List<Reserva> expected=rs.listarReservasCalendario(Horas.PRIMERA, weekOfDate, aula);
		
		assertEquals(expected, mockList, "El test para proporcionar un List de reservas de un aula, en una fecha y hora concreta no funciona correctamente");

	}
	
	@Test
	public void testListarReservasCalendarioValoresNull() {

		assertThat(rs.listarReservasCalendario(null, null, null).isEmpty()).isTrue();
	}
	
	@Test
	public void testListarReservasCalendarioFechaNull() {
		
		assertThrows(NullPointerException.class,()->rs.listarReservasCalendario(Horas.CUARTA, null, new Aula()));
	}
	
	@Test
	public void testListarReservasCalendarioHorasNull() {
		boolean resul=rs.listarReservasCalendario(null, weekOfDate, new Aula()).stream().allMatch(x->x==null);
		
		assertThat(resul).isTrue();
		assertThat(rs.listarReservasCalendario(null, weekOfDate, new Aula()).size()==7).isTrue();
	}
	
	@Test
	public void testListarReservasCalendarioAulaNull() {
		
		assertThat(rs.listarReservasCalendario(Horas.CUARTA, weekOfDate, null).isEmpty()).isTrue();
	}
	
	@Test
	public void testListarHorasLibres() {
		Aula aula=new Aula();
		List<Reserva> mockList=Arrays.asList(
				new Reserva(Horas.CUARTA, aula, usuario1, date),
				new Reserva(Horas.PRIMERA,aula, usuario1, date)
				);
		
		when(rr.findByFechaAndAula(date, aula)).thenReturn(mockList);
		
		List<Horas> expected=rs.listarHorasLibres(date, aula);
		List<Horas> mockHoras=Arrays.asList(Horas.SEGUNDA,Horas.TERCERA,Horas.QUINTA,Horas.SEXTA);
		
		assertEquals(expected, mockHoras);
		
	}
	@Test
	public void testListarHorasLibresValoresNull() {
		List <Horas> mockHoras=Arrays.asList(Horas.values());
		
		assertThat(rs.listarHorasLibres(null, null).equals(mockHoras)).isTrue();
		assertDoesNotThrow(()->rs.listarHorasLibres(null, null));
		
	}
	
	@Test
	public void testListarHorasLibresFechaNull() {
		List <Horas> mockHoras=Arrays.asList(Horas.values());
		Aula aula=new Aula();
		
		assertThat(rs.listarHorasLibres(null, aula).equals(mockHoras)).isTrue();
		assertDoesNotThrow(()->rs.listarHorasLibres(null, aula));
		
	}
	
	@Test
	public void testListarHorasLibresAulaNull() {
		List <Horas> mockHoras=Arrays.asList(Horas.values());

		assertThat(rs.listarHorasLibres(date, null).equals(mockHoras)).isTrue();
		assertDoesNotThrow(()->rs.listarHorasLibres(date, null));
		
	}
	
	
	@Test
	public void testComprobarReservaImposibleNoEncuentraReservaImposible() {
		
		when(rr.findByUsuarioAndFechaAndHora(usuario1, date, Horas.PRIMERA)).thenReturn(null);
		assertThat(rs.comprobarReservaImposible(usuario1, date, Horas.PRIMERA)).isFalse();
	}
	
	@Test
	public void testComprobarReservaImposibleEncuentraReservaImposible() {
		Reserva r=new Reserva(Horas.PRIMERA,new Aula(),usuario1,date);
		when(rr.findByUsuarioAndFechaAndHora(usuario1, date, Horas.PRIMERA)).thenReturn(r);
		assertThat(rs.comprobarReservaImposible(usuario1, date, Horas.PRIMERA)).isTrue();
	}
	
	@Test
	public void testComprobarReservaImposibleDatosNulos() {
		assertDoesNotThrow(()->rs.comprobarReservaImposible(null, null, null));
		assertThat(rs.comprobarReservaImposible(null,null,null)).isFalse();

	}
	
	@Test
	public void testComprobarReservaImposibleHoraNull() {
		assertDoesNotThrow(()->rs.comprobarReservaImposible(usuario1, date, null));
		assertThat(rs.comprobarReservaImposible(usuario1, date, null)).isFalse();

	}
	
	@Test
	public void testComprobarReservaImposibleUsuarioNull() {
		assertDoesNotThrow(()->rs.comprobarReservaImposible(null, date, Horas.PRIMERA));
		assertThat(rs.comprobarReservaImposible(null, date, Horas.PRIMERA)).isFalse();

	}
	
	@Test
	public void testComprobarReservaImposibleFechaNull() {
		assertDoesNotThrow(()->rs.comprobarReservaImposible(usuario1, null, Horas.PRIMERA));
		assertThat(rs.comprobarReservaImposible(usuario1, null, Horas.PRIMERA)).isFalse();

	}
	
	@Test
	public void testBuscarReservasFuturas() {
		Reserva reserva1=new Reserva(Horas.CUARTA, new Aula(), usuario1, date);
		Reserva reserva2=new Reserva(Horas.PRIMERA, new Aula(), usuario1, LocalDate.of(2050, 10, 27));
		List<Reserva> mockList=Arrays.asList(reserva1, reserva2 );
		
		when(rr.findAll()).thenReturn(mockList);
		
		List<Reserva> expected=	rs.buscarReservasFuturas();
		
		assertThat(expected.size()==1).isTrue();
		assertThat(expected.contains(reserva1)).isFalse();
		assertThat(expected.contains(reserva2)).isTrue();
		
	}
	
	@Test
	public void testBuscarReservasFuturasFechaActualYFechaPasadas() {
		Reserva reserva1=new Reserva(Horas.CUARTA, new Aula(), usuario1, LocalDate.of(2018, 10, 27));
		Reserva reserva2=new Reserva(Horas.PRIMERA, new Aula(), usuario1, LocalDate.now());
		List<Reserva> mockList=Arrays.asList(reserva1, reserva2 );
		
		when(rr.findAll()).thenReturn(mockList);
		
		List<Reserva> expected=	rs.buscarReservasFuturas();
		
		assertThat(expected.size()==1).isTrue();
		assertThat(expected.contains(reserva1)).isFalse();
		assertThat(expected.contains(reserva2)).isTrue();
	}
	
	@Test
	public void testBuscarReservasFuturasNoHayReservas() {
		List<Reserva> mockList=new ArrayList<Reserva>();
		
		when(rr.findAll()).thenReturn(mockList);
		
		List<Reserva> expected=	rs.buscarReservasFuturas();
		
		assertThat(expected.isEmpty()).isTrue();
		assertDoesNotThrow(()->rs.buscarReservasFuturas());
		
	}
	


	@Test
	public void testBuscarReservasFuturasFechaNula() {
		Reserva reserva1=new Reserva(Horas.CUARTA, new Aula(), usuario1, null);
		Reserva reserva2=new Reserva(Horas.PRIMERA, new Aula(), usuario1, LocalDate.now());
		List<Reserva> mockList=Arrays.asList(reserva1, reserva2 );
		
		when(rr.findAll()).thenReturn(mockList);
		
		assertThrows(NullPointerException.class,()->rs.buscarReservasFuturas());
		
	}
	
	@Test
	public void testBuscarReservasPasadas() {
		Reserva reserva1=new Reserva(Horas.CUARTA, new Aula(), usuario1, date);
		Reserva reserva2=new Reserva(Horas.PRIMERA, new Aula(), usuario1, LocalDate.of(2050, 10, 27));
		List<Reserva> mockList=Arrays.asList(reserva1, reserva2 );
		
		when(rr.findAll()).thenReturn(mockList);
		
		List<Reserva> expected=	rs.buscarReservasPasadas();
		
		assertThat(expected.size()==1).isTrue();
		assertThat(expected.contains(reserva2)).isFalse();
		assertThat(expected.contains(reserva1)).isTrue();

	}
	
	@Test
	public void testBuscarReservasPasadasFechaActualYFechaFutura() {
		Reserva reserva1=new Reserva(Horas.CUARTA, new Aula(), usuario1, LocalDate.of(2050, 10, 27));
		Reserva reserva2=new Reserva(Horas.PRIMERA, new Aula(), usuario1, LocalDate.now());
		List<Reserva> mockList=Arrays.asList(reserva1, reserva2 );
		
		when(rr.findAll()).thenReturn(mockList);
		
		List<Reserva> expected=	rs.buscarReservasPasadas();
		
		assertThat(expected.isEmpty()).isTrue();
	}
	
	@Test
	public void testBuscarReservasPasadasNoHayReservas() {
		List<Reserva> mockList=new ArrayList<Reserva>();
		
		when(rr.findAll()).thenReturn(mockList);
		
		List<Reserva> expected=	rs.buscarReservasPasadas();
		
		assertThat(expected.isEmpty()).isTrue();
		assertDoesNotThrow(()->rs.buscarReservasPasadas());
		
	}
	
	@Test
	public void testBuscarReservasPasadasFechaNula() {
		Reserva reserva1=new Reserva(Horas.CUARTA, new Aula(), usuario1, null);
		Reserva reserva2=new Reserva(Horas.PRIMERA, new Aula(), usuario1, date);
		List<Reserva> mockList=Arrays.asList(reserva1, reserva2 );
		
		when(rr.findAll()).thenReturn(mockList);
		
		assertThrows(NullPointerException.class,()->rs.buscarReservasPasadas());
		
	}
	
	//PREGUNTAR A LUISMI SI VERIFY ES UN TIPO DE ASSERT
	@Test
	public void testEliminarReservaPorAula() {
		Reserva reservaMock=new Reserva(Horas.CUARTA, new Aula(), usuario1, date);
		List<Reserva> mockList=Arrays.asList(reservaMock);
		
		when(rr.findByAula(reservaMock.getAula())).thenReturn(mockList);
		rs.eliminarReservasPorAula(reservaMock.getAula());
		
		verify(rr, times(1)).deleteAll(mockList);
	}
	
	@Test
	public void testEliminarReservaPorAulaNull() {
		assertDoesNotThrow(() -> rs.eliminarReservasPorAula(null));
	}
	
	@Test
	public void testEliminarReservaPorAulaVacia() {
		assertDoesNotThrow(() -> rs.eliminarReservasPorAula(new Aula()));
		
	}
	
	@Test
	public void testEliminarReservaPorAulaNoHayReservas() {
		List<Reserva> mockList=new ArrayList<Reserva>();	
		Aula aula=new Aula();
		when(rr.findByAula(aula)).thenReturn(mockList);
		assertDoesNotThrow(()->rs.eliminarReservasPorAula(aula));
		
		
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
	
	@Test
	public void testListarReservasPorUsuarioNulo() {
		List<Reserva> expected=rr.findByUsuario(null);
		
		assertThat(expected.isEmpty()).isTrue();
		assertDoesNotThrow(() -> expected.size());
		
	}
	@Test
	public void testListarReservasPorUsuarioVacio() {
		List<Reserva> expected=rr.findByUsuario(new Usuario());
		
		assertThat(expected.isEmpty()).isTrue();
		assertDoesNotThrow(() -> expected.size());
	}
	
}
