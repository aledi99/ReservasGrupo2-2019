/*package com.salesianostriana.reservas.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

pubic class AulasController {
	
	@GetMapping("/user/inicio")
	public String mostrarInicio(Model model, Principal p) {
		model.addAttribute("p",p);
		model.addAttribute("listAulas", as.findAll());
		return "user/inicio";
	}
}
*/