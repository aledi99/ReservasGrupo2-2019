package com.salesianostriana.reservas.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.service.ReservaServicio;
import com.salesianostriana.reservas.service.UsuarioServicio;
/**
 * Esta clase gestiona las peticiones del perfil de usuario
 * @author J Manuel Terrero
 *
 */
@Controller
public class PerfilController {
	
	@Autowired
	UsuarioServicio us;
	@Autowired
	ReservaServicio rs;
	/**
	 * Muestra la página del perfil
	 * @param model
	 * @param p 
	 * @return
	 */
	@GetMapping("/user/perfil")
	public String mostrarPerfil(Model model, Principal p) {
		model.addAttribute("listReservas", rs.listarReservasPorUsuario(us.buscarUsuarioLogged(p)));
		model.addAttribute("usuario", us.buscarUsuarioLogged(p));
		return "user/perfil";
	}
	/**
	 * Borra una reserva listada en el perfil
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/user/borrar/{id}")
	public String borrar(@PathVariable("id") long id, Model model) {
		rs.delete(rs.findById(id));
		return "redirect:/user/perfil";
	}
	/**
	 * Edita el perfil
	 * @param model
	 * @param p
	 * @return
	 */
	@GetMapping("/user/perfil/editPerfil")
	public String editPerfil(Model model, Principal p) {
		model.addAttribute("usuario", us.buscarUsuarioLogged(p));
		return "user/editForm";
	}
	/**
	 * Hace post del editar perfil
	 * @param u Usuario a editar
	 * @param model
	 * @return
	 */
	@PostMapping("/user/perfil/editPerfil/submit")
	public String editPerfilSubmit(@ModelAttribute("usuario") Usuario u, Model model) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		u.setPassword(encoder.encode(u.getPassword()));
		u.setGestionado(true);
		us.edit(u);
		return "redirect:/user/perfil";
	}
}
