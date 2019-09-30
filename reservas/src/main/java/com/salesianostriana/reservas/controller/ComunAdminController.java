package com.salesianostriana.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Esta clase gestiona la petición del menú de inicio del administrador
 * @author Álvaro Márquez
 *
 */
@Controller
@RequestMapping("/admin")
public class ComunAdminController {

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

	@GetMapping("/inicio")
	public String portada() {
		return "admin/pantalla1";
	}

}
