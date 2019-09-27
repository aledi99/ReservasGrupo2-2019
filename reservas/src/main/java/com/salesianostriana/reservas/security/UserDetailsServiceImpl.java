/**
 * 
 */
package com.salesianostriana.reservas.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.service.UsuarioServicio;

/**
 * Clase que gestiona la información del usuario actual.
 * 
 * @author Esperanza M Escacena M
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	UsuarioServicio usuarioServicio;

	public UserDetailsServiceImpl(UsuarioServicio servicio) {
		this.usuarioServicio = servicio;
	}
	/**
	 * Método que asignará roles. Sólo si el usuario está gestionado se le asignará un rol.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioServicio.buscarPorEmail(username);

		UserBuilder userBuilder = null;

		if (usuario != null) {
			userBuilder = User.withUsername(usuario.getEmail());
			userBuilder.disabled(false);
			userBuilder.password(usuario.getPassword());
			if (usuario.isGestionado()) {
				if (usuario.isAdmin()) {
					
					userBuilder.authorities(
							new SimpleGrantedAuthority("ROLE_ADMIN"));
				} else {
					userBuilder.authorities(new SimpleGrantedAuthority("ROLE_USER"));
				}
			}
		} else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}

		return userBuilder.build();
	}
}
