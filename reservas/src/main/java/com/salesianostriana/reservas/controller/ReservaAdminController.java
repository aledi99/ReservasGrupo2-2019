package com.salesianostriana.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class ReservaAdminController {
	
	/**
	 * OJO A LA ANOTACIÓN DE CLASE "@RequestMapping("/admin")". Todos los @GetMapping 
	 * 
	 * que se incluyan en esta clase irán precedidos de "/admin". Es decir, 
	 * 
	 * si creas un método anotado con "@GetMapping("/aulas")" la URL para llegar 
	 * 
	 * a la plantilla que devuelva NO será "localhost:9000/aulas", sino que será
	 * 
	 * "localhost:9000/admin/aulas".
	 */
	
}
