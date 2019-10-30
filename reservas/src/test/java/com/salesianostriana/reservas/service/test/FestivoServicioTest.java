package com.salesianostriana.reservas.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.salesianostriana.reservas.model.Festivo;
import com.salesianostriana.reservas.repository.FestivoRepository;
import com.salesianostriana.reservas.service.FestivoServicio;


@SpringBootTest
public class FestivoServicioTest {

	@InjectMocks
	private FestivoServicio fs;
	
	@Mock
	private FestivoRepository fr;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public final void testBuscarSabadosYDomingos() {
		List<LocalDate> lista = fs.BuscarSabadosYDomingos();
		List<DayOfWeek> sabYDom = new ArrayList<>();
		sabYDom.add(DayOfWeek.SATURDAY);
		sabYDom.add(DayOfWeek.SUNDAY);
		
		assertThat(lista.isEmpty()).isFalse();
		
		for (LocalDate d : lista) {
			assertThat(sabYDom.contains(d.getDayOfWeek())).isTrue();
		}
	}
	
	@Test
	public final void testBuscarSabados() {
		List<LocalDate> lista = fs.BuscarSabados();
		List<DayOfWeek> sab = new ArrayList<>();
		sab.add(DayOfWeek.SATURDAY);

		assertThat(lista.isEmpty()).isFalse();
		
		for (LocalDate d : lista) {
			assertThat(sab.contains(d.getDayOfWeek())).isTrue();
		}
	}
	
	@Test
	public final void testBuscarDomingos() {
		List<LocalDate> lista = fs.BuscarDomingos();
		List<DayOfWeek> dom = new ArrayList<>();
		dom.add(DayOfWeek.SUNDAY);

		assertThat(lista.isEmpty()).isFalse();
		
		for (LocalDate d : lista) {
			assertThat(dom.contains(d.getDayOfWeek())).isTrue();
		}
	}
	
	@Test
	public final void testComprobarFestivo() {
		List<Festivo> festivos = new ArrayList<>();
		Festivo f = new Festivo(1L, LocalDate.now(), true);
		festivos.add(f);
		when(fr.findAll()).thenReturn(festivos);
		
		assertThat(fs.comprobarFestivo(LocalDate.now())).isTrue();
		
		assertThat(fs.comprobarFestivo(LocalDate.of(1900, 1, 1))).isFalse();
	}
	
	@Test
	public final void testListarFestivosSinFinesDeSemana() {
		List<Festivo> festivos = new ArrayList<>();
		Festivo listar = new Festivo(1L, LocalDate.now(), true);
		Festivo noListar = new Festivo(2L, LocalDate.now(), false);
		festivos.add(listar);
		festivos.add(noListar);
		
		when(fr.findAll()).thenReturn(festivos);
		
		List<Festivo> resultados = fs.listarFestivosSinFinesDeSemana();
		
		assertThat(resultados.contains(listar));
		
		assertThat(resultados.contains(noListar)).isFalse();
		
	}
	
	
	
	
	
	
	

}
