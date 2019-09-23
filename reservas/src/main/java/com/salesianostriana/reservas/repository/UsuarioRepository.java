package com.salesianostriana.reservas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesianostriana.reservas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Usuario findFirstByEmail(String email);

}
