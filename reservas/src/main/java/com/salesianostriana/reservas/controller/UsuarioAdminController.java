package com.salesianostriana.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.service.UsuarioServicio;
/**
 * Esta clase gestiona las peticiones del administrador sobre la gestión del usuario
 * @author Álvaro Márquez
 *
 */
@Controller
@RequestMapping("/admin")
public class UsuarioAdminController {
	
	/*
	 * OJO A LA ANOTACIÓN DE CLASE "@RequestMapping("/admin")". Todos
	 * los @GetMapping
	 * 
	 * que se incluyan en esta clase irán precedidos de "/admin". Es decir,
	 * 
	 * si creas un método anotado con "@GetMapping("/aulas")" la URL para llegar
	 * 
	 * a la plantilla que devuelva NO será "localhost:9000/aulas", sino que será
	 * 
	 * "localhost:9000/admin/aulas".
	 */

	private UsuarioServicio usuarioservicio;
	
	public UsuarioAdminController(UsuarioServicio usuarioservicio) {
		this.usuarioservicio = usuarioservicio;
	}
	
	
	@ModelAttribute("usuarios")
	public void listaSolicitudes(Model model) {
		model.addAttribute("usuarios", usuarioservicio.buscarPorGestionadoFalse());
	}
	/**
	 * Muestra la plantilla donde se muestra las solicitudes de usuarios (no gestionados)
	 * @param model
	 * @return
	 */
	@GetMapping("/solicitudes")
	public String solicitudes(Model model) {
		
		model.addAttribute("usuarios");
		return "admin/solicitudes";

	}
	/**
	 * Elimina la solicitud de un usuario
	 * @param id ID del usuario a eliminar
	 * @param model
	 * @return Redirige a la página principal de solicitudes
	 */
	@GetMapping("/eliminarSolicitud/{id}")
	public String eliminarSolicitud(@PathVariable("id") long id, Model model) {
		usuarioservicio.deleteById(id);
		
		model.addAttribute("usuarios");
		return "redirect:/admin/solicitudes";
	}
	/**
	 * Valida una solicitud
	 * @param id ID del usuario a eliminar
	 * @param model
	 * @return Redirige a la página principal de solicitudes
	 */
	@PostMapping("/validar/{id}")
	public String validarUsuario(@PathVariable("id") long id, Model model) {
		Usuario usuario = usuarioservicio.findById(id);
		usuario.setGestionado(true);
		usuarioservicio.edit(usuario);
		
		model.addAttribute("usuarios");
		return "redirect:/admin/solicitudes";
	}
	/**
	 * Muestra la plantilla de usuarios
	 * @param model
	 * @return
	 */
	@GetMapping("/usuarios")
	public String usuarios(Model model) {
		model.addAttribute("usuarios", usuarioservicio.buscarPorGestionadoTrueAndAdminFalse());
		
		return "admin/usuarios";
	}

}
