package com.salesianostriana.reservas.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PerfilController {
	@GetMapping("/user/perfil")
	public String mostrarPerfil(Model model, Principal p) {
		model.addAttribute("p",p);
		return "user/perfil";
	}
}
