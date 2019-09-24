package com.salesianostriana.reservas.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesianostriana.reservas.model.Reserva;
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
		model.addAttribute("listUsuario", us.findAll());
		model.addAttribute("listReservas", rs.findAll());
		model.addAttribute("usuario", us.buscarUsuarioLogged(p));
		return "user/perfil";
	}
	
	@GetMapping("/user/borrar/{id}")
	public String borrar(@PathVariable("id") long id, Model model) {
		rs.delete(rs.findById(id));
		return "redirect:/user/perfil";
	}
}
