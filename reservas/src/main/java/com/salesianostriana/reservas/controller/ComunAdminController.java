package com.salesianostriana.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComunAdminController {

	@GetMapping("/inicio")
	public String portada() {
		return "admin/pantalla1";
	}

}
