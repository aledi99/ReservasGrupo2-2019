package com.salesianostriana.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.service.AulaServicio;

@Controller
@RequestMapping("/admin")
public class AulasAdminController {
	
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
	
	private AulaServicio aulaservicio;
	
	public AulasAdminController(AulaServicio aulaservicio) {
		this.aulaservicio = aulaservicio;
	}
	
	@GetMapping("/espacios")
	public String espacios(Model model) {
		model.addAttribute("espacios", aulaservicio.findAll());
		
		return "admin/espacios";
	}
	
	@GetMapping("/newAula")
	public String newAula(Model model) {
		model.addAttribute("aula", new Aula());
		
		return "admin/SignUpAula";
	}
	
	@PostMapping("/newAula/submit")
	public String procesarAula(@ModelAttribute("aula") Aula a) {
		aulaservicio.save(a);
		
		return "redirect:/admin/espacios";
	}
	

}
