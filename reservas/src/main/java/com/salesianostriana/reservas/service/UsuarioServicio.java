package com.salesianostriana.reservas.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.repository.UsuarioRepository;
/**
 * 
 * @author Alejandro Díaz
 *
 */
@Service
public class UsuarioServicio extends ServicioBase<Usuario, Long, UsuarioRepository> {
	/**
	 * Busca al usuario por su email
	 * @param email Email del usuario
	 * @return
	 */
	public Usuario buscarPorEmail(String email) {
		return repositorio.findFirstByEmail(email);
	}
	/**
	 * Comprueba si ya existe en la BBDD el email introducido en el signup
	 * Hecho por Esperanza Escacena
	 * @param email Email del usuario a registrar
	 * @return
	 */
	public boolean comprobarEmail(String email) {
		if (this.findAll().stream().anyMatch(usuario -> usuario.getEmail().equals(email))) {
			return true;
		} else {
			return false;
		}

	}
	/**
	 * Para obtener el usuario logueado
	 * @param p
	 * @return
	 */
	public Usuario buscarUsuarioLogged(Principal p) {
		Usuario usuario;
		if (p != null) {
			usuario = this.buscarPorEmail(p.getName());
			return usuario;
		} else {
			return null;
		}
	}
	/**
	 * Realizado por Álvaro Márquez. Busca los usuarios gestionados no administradores
	 * @return
	 */
	public Page<Usuario> buscarPorGestionadoTrueAndAdminFalse(Pageable pageable) {
		return repositorio.findAllByGestionadoTrueAndAdminFalse(pageable);
	}
	/**
	 * Realizado por Álvaro Márquez. Busca los usuarios no gestionados.
	 * @return
	 */
	public Page<Usuario> buscarPorGestionadoFalse(Pageable pageable) {
		return repositorio.findAllByGestionadoFalse(pageable);
	}
	
	public List<Usuario> buscarPorGestionadoFalse() {
		return repositorio.findAllByGestionadoFalse();
	}
}
