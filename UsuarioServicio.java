package com.salesianostriana.reservas.service;

import org.springframework.stereotype.Service;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.repository.UsuarioRepository;

@Service
public class UsuarioServicio extends ServicioBase<Usuario, Long, UsuarioRepository> {

	public Usuario buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}

	public boolean comprobarEmail(String email) {
		if (this.findAll().stream().anyMatch(usuario -> usuario.getEmail().equals(email))) {
			return true;
		} else {
			return false;
		}

	}
}
