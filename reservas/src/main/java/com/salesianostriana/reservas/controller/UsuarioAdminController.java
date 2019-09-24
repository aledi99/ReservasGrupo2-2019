package com.salesianostriana.reservas.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.service.UsuarioServicio;

@Controller
@RequestMapping("/admin")
public class UsuarioAdminController {

	private UsuarioServicio usuarioservicio;

	public UsuarioAdminController(UsuarioServicio usuarioservicio) {
		this.usuarioservicio = usuarioservicio;
	}
	
	@ModelAttribute("usuarios")
	public void listaSolicitudes(Model model) {
		model.addAttribute("usuarios", usuarioservicio.buscarPorGestionadoFalse());
	}
	

	@GetMapping("/solicitudes")
	public String solicitudes(Model model) {
		
		model.addAttribute("usuarios");
		return "admin/solicitudes";

	}
	
	@GetMapping("/eliminarSolicitud/{id}")
	public String eliminarSolicitud(@PathVariable("id") long id, Model model) {
		usuarioservicio.deleteById(id);
		
		model.addAttribute("usuarios");
		return "redirect:/admin/solicitudes";
	}
	
	@PostMapping("/validar/{id}")
	public String validarUsuario(@PathVariable("id") long id, Model model) {
		Usuario usuario = usuarioservicio.findById(id);
		usuario.setGestionado(true);
		usuarioservicio.edit(usuario);
		
		model.addAttribute("usuarios");
		return "redirect:/admin/solicitudes";
	}

	@GetMapping("/usuarios")
	public String usuarios() {
		return "admin/usuarios";
	}

}
