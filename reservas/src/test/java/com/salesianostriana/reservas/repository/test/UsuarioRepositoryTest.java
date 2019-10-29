package com.salesianostriana.reservas.repository.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.repository.UsuarioRepository;

@DataJpaTest
//@Sql("sql/data2.sql")
public class UsuarioRepositoryTest {
	@Autowired
	private UsuarioRepository ur;

	@Test
	void findFirstByEmail() {
		Usuario us=ur.findFirstByEmail("usuario@usuario.com");
		assertThat(us).isNotNull();
		assertThat(us.getEmail()).isEqualTo("usuario@usuario.com");
	}
	@Test
	void findFirstByEmailVacio() {
		Usuario us=ur.findFirstByEmail("");
		assertThat(us).isNull();
	
	}
	@Test
	void findFirstByEmailNoExiste() {
		Usuario us=ur.findFirstByEmail("gaga@gaga.com");
		assertThat(us).isNull();
	
	}
	@Test
	void findFirstByEmailNull() {
		Usuario us=ur.findFirstByEmail(null);
		assertThat(us).isNull();
	
	}
	
	@Test
	public void buscarAllPorGestionadoTrueYAdminFalse() {
		List<Usuario> usuarios=ur.findAllByGestionadoTrueAndAdminFalse();
		List<Boolean> gestionados=usuarios.stream().map(x->x.isGestionado()).collect(Collectors.toList());
		List<Boolean> admin=usuarios.stream().map(x->x.isAdmin()).collect(Collectors.toList());

		assertThat(gestionados.contains(false)).isFalse();
		assertThat(admin.contains(true)).isFalse();
	}
	
	@Test
	public void buscarAllPorGestionadoFalse() {
		List<Usuario> usuarios=ur.findAllByGestionadoFalse();
		List<Boolean> gestionados=usuarios.stream().map(x->x.isGestionado()).collect(Collectors.toList());
		assertThat(gestionados.contains(true)).isFalse();
	}
	
	
}
