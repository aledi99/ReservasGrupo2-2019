package com.salesianostriana.reservas.service.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.repository.AulaRepository;
import com.salesianostriana.reservas.service.AulaServicio;
import com.salesianostriana.reservas.service.ReservaServicio;

@SpringBootTest
public class AulaServicioTest {

	@InjectMocks
	private AulaServicio as;
	
	@Mock
	private AulaRepository ar;
	
	@Mock
	private ReservaServicio rs;
	
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testEliminarAula() {
		Aula aulaMock = new Aula();
		
		as.eliminarAula(aulaMock);
		
		verify(ar, times(1)).delete(aulaMock);
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
