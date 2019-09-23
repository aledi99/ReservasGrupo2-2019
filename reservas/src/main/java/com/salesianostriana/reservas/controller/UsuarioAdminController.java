package com.salesianostriana.reservas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.service.UsuarioServicio;

@Controller
public class UsuarioAdminController {

	private UsuarioServicio usuarioservicio;

	public UsuarioAdminController(UsuarioServicio usuarioservicio) {
		this.usuarioservicio = usuarioservicio;
	}
	
	@ModelAttribute("usuarios")
	public List<Usuario> listaSolicitudes() {
		return usuarioservicio.buscarPorGestionadoFalse();
	}
	

	@GetMapping("/solicitudes")
	public String solicitudes(Model model) {
		List<Usuario> usuarios = usuarioservicio.buscarPorGestionadoFalse();

		model.addAttribute("usuarios", usuarios);
		return "admin/solicitudes";

	}
	
	@GetMapping("/eliminarSolicitud/{id}")
	public String eliminarSolicitud(@PathVariable("id") long id) {
		usuarioservicio.deleteById(id);
		
		
		return "";
	}

	@GetMapping("/usuarios")
	public String usuarios() {
		return "admin/usuarios";
	}

}
