package com.salesianostriana.reservas.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.reservas.service.AulaServicio;
import com.salesianostriana.reservas.service.UsuarioServicio;

@Controller
public class AulasController {

	@Autowired
	AulaServicio as;
	@Autowired
	UsuarioServicio us;

	@GetMapping("/user/inicio")
	public String mostrarInicio(Model model, Principal p) {

		model.addAttribute("listAulas", as.findAll());

		model.addAttribute("usuario", us.buscarUsuarioLogged(p));
		return "user/inicio";
	}
}
