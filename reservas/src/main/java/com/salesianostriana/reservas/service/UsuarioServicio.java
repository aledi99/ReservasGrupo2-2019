package com.salesianostriana.reservas.service;

import java.security.Principal;

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
	
	public Usuario buscarUsuarioLogged(Principal p) {
        Usuario usuario;
        if(p!=null) {
            usuario=this.buscarPorEmail(p.getName());
            return usuario;
        }else {
            return null;
        }
    }
}
