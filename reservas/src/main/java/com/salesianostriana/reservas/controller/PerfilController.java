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
@Controller
public class PerfilController {
	
	@Autowired
	UsuarioServicio us;
	@Autowired
	ReservaServicio rs;
	
	@GetMapping("/user/perfil")
	public String mostrarPerfil(Model model, Principal p) {
		model.addAttribute("listReservas", rs.findAll());
		model.addAttribute("usuario", us.buscarUsuarioLogged(p));
		return "user/perfil";
	}
	
	@GetMapping("/user/borrar/{id}")
	public String borrar(@PathVariable("id") long id, Model model) {
		rs.delete(rs.findById(id));
		return "redirect:/user/perfil";
	}
	
	@GetMapping("/user/perfil/editPerfil")
	public String editPerfil(Model model, Principal p) {
		model.addAttribute("usuario", us.buscarUsuarioLogged(p));
		return "pagEstaticas/antiguo/Sign Up";
	}

	@PostMapping("/user/perfil/editPerfil/submit")
	public String editPerfilSubmit(@ModelAttribute("usuario") Usuario u, Model model) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		u.setPassword(encoder.encode(u.getPassword()));
		us.edit(u);
		return "redirect:/user/perfil";
	}
}
