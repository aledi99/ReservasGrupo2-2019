package com.salesianostriana.reservas.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.reservas.model.Usuario;
/**
 * Repositorio de Usuario.
 * @author Esperanza M Escacena M
 *
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findFirstByEmail(String email);

	public List<Usuario> findAllByGestionadoTrueAndAdminFalse();

	public List<Usuario> findAllByGestionadoFalse();
	
	public Page<Usuario> findAllByGestionadoTrueAndAdminFalse(Pageable pageable);
	
	public Page<Usuario> findAllByGestionadoFalse(Pageable pageable);

}
