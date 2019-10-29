package com.salesianostriana.reservas.service.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
import com.salesianostriana.reservas.repository.AulaRepository;
import com.salesianostriana.reservas.service.AulaServicio;

@SpringBootTest
public class AulaServicioTest {

	@InjectMocks
	private AulaServicio as;
	
	@Mock
	private AulaRepository ar;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	private String nombreAula = "Aula 1";

	@Test
	public void testEliminarAula() {
		Aula aulaMock = new Aula();
		
		List<Aula> mockList = Arrays.asList(aulaMock);
		
		// when(ar.findAula(aulaMock)).thenReturn(mockList);
		as.eliminarAula(aulaMock);
		
		verify(ar, times(1)).deleteAll(mockList);
	}
	
	@Test
	public void testEliminarAulaNull() {
		assertDoesNotThrow(() -> as.eliminarAula(null));
	}
	
	@Test
	public void testEliminarAulaVacia() {
		assertDoesNotThrow(() -> as.eliminarAula(new Aula()));
		
	}
	
}
