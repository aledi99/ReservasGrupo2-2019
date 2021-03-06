package com.salesianostriana.reservas.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.repository.UsuarioRepository;
import com.salesianostriana.reservas.service.UsuarioServicio;

@SpringBootTest
public class UsuarioServicioTest {

	@InjectMocks
	private UsuarioServicio us;

	@Mock
	private Principal principal;

	@Mock
	private User user;

	@Mock
	private UsuarioRepository ur;

	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	private String correo = "admin@admin.com";
	
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	@Test
	public void testBuscarPorEmail() {
		Usuario mockUsuario = new Usuario("admin@admin.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Luis Miguel", "López Magaña", true);

		when(ur.findFirstByEmail(correo)).thenReturn(mockUsuario);

		Usuario expected = us.buscarPorEmail(correo);

		assertEquals(expected, mockUsuario, "El test no ha sido satisfactorio.");

	}

	@Test
	public void testBuscarPorEmailNull() {
		assertEquals(null, null);
	}

	@Test
	public void testComprobarEmail() {
		Usuario mockUsuario = new Usuario("admin@admin.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Luis Miguel", "López Magaña", true);
		Usuario mockUsuario2 = new Usuario("usuario@usuario.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Luis Miguel", "López Magaña", true);

		List<Usuario> mockList = new ArrayList<Usuario>();
		mockList.add(mockUsuario);
		mockList.add(mockUsuario2);

		when(ur.findAll()).thenReturn(mockList);

		boolean expected = us.comprobarEmail(correo);

		assertEquals(expected, true, "El test no ha sido satisfactorio.");

	}

	@Test
	public void testComprobarEmailIncorrecto() {
		Usuario mockUsuario = new Usuario("salesianostriana@gmail.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Luis Miguel", "López Magaña", true);
		Usuario mockUsuario2 = new Usuario("usuario@usuario.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Luis Miguel", "López Magaña", true);

		List<Usuario> mockList = new ArrayList<Usuario>();
		mockList.add(mockUsuario);
		mockList.add(mockUsuario2);

		when(ur.findAll()).thenReturn(mockList);

		boolean expected = us.comprobarEmail(correo);

		assertEquals(expected, false, "El test no ha sido satisfactorio.");
	}

	@Test
	public void testBuscarUsuarioLogged() {
		Usuario mockUsuario = new Usuario("salesianostriana@gmail.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Luis Miguel", "López Magaña", true);

		when(principal.getName()).thenReturn("usuario@usuario.com");
		when(us.buscarUsuarioLogged(principal)).thenReturn(mockUsuario);
		
		Usuario expected = us.buscarUsuarioLogged(principal);
		
		assertEquals(expected, mockUsuario, "El test no ha sido satisfactorio.");
		
	}
	
	@Test
	public void testBuscarUsuarioLoggedNoUsuarios() {
		Usuario mockUsuario = null;
		
		when(principal.getName()).thenReturn("usuario@usuario.com");
		when(us.buscarUsuarioLogged(principal)).thenReturn(mockUsuario);
		
		Usuario expected = us.buscarUsuarioLogged(principal);
		
		assertEquals(expected, mockUsuario, "El test no ha sido satisfactorio.");
	}
	
	@Test
	public void testBuscarUsuarioLoggedNoPrincipal() {
		assertEquals(us.buscarUsuarioLogged(null), null, "El test no ha sido satisfactorio.");
	}
	
	@Test
	public void testBuscarPorGestionadoTrueAndAdminFalse() {
		//PREGUNTAR EN CLASE ACERCA DE LOS TEST'S CON PAGE
	}
	
	@Test
	public void testBuscarPorGestionadoFalsePage() {
		//PREGUNTAR EN CLASE ACERCA DE LOS TEST'S CON PAGE
	}
	
	@Test
	public void testBuscarPorGestionadoFalseList() {
		Usuario mockUsuario = new Usuario("salesianostriana@gmail.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Luis Miguel", "López Magaña", false);
		Usuario mockUsuario2 = new Usuario("usuario@usuario.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Luis Miguel", "López Magaña", false);
		Usuario mockUsuario3 = new Usuario("admin@admin.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Angel", "Naranjo", true);
		
		List<Usuario> mockList = new ArrayList<Usuario>();
		mockList.add(mockUsuario);
		mockList.add(mockUsuario2);
		
		when(ur.findAllByGestionadoFalse()).thenReturn(mockList);
		
		List<Usuario> expected = us.buscarPorGestionadoFalse();
		
		assertEquals(expected, mockList, "El test no ha sido satisfactorio.");
	}
	
	@Test
	public void testBuscarPorGestionadoFalseListNull() {
		Usuario mockUsuario = new Usuario("salesianostriana@gmail.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Luis Miguel", "López Magaña", false);
		Usuario mockUsuario2 = new Usuario("usuario@usuario.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Luis Miguel", "López Magaña", false);
		Usuario mockUsuario3 = new Usuario("admin@admin.com",
				"$2a$10$Q.R0bxAjLZRakEAsV7ODCO26e1cCKRyMUdtnWev4y3zSZgxFZOHLK", "Angel", "Naranjo", true);
		
		List<Usuario> mockList = null;
		
		when(ur.findAllByGestionadoFalse()).thenReturn(mockList);
		
		List<Usuario> expected = us.buscarPorGestionadoFalse();
		
		assertEquals(expected, mockList, "El test no ha sido satisfactorio.");
	}
	
	
	
	

}
