package com.salesianostriana.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.salesianostriana.reservas.service.UsuarioServicio;
@Controller
public class LoginController {
	@Autowired
	UsuarioServicio s;
	@GetMapping("/")
	public String getLogin(Model model) {
		
		return "pagEstaticas/Login2";
	}
}
