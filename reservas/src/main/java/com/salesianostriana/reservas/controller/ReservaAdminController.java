package com.salesianostriana.reservas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.reservas.service.ReservaServicio;
/**
 * Esta clase gestiona las peticiones del administrador sobre las reservas
 * @author Álvaro Márquez
 *
 */
@Controller
@RequestMapping("/admin")
public class ReservaAdminController {
	
	private ReservaServicio reservaservicio;
	
	public ReservaAdminController(ReservaServicio reservaservicio) {
		this.reservaservicio = reservaservicio;
	}
	
	/*
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
	/**
	 * Muestra la plantilla donde se reservan las reservas en la ventana de administrador
	 * @param model
	 * @return
	 */
	@GetMapping("/reservas")
	public String reservas(Model model) {
		model.addAttribute("reservas", reservaservicio.buscarReservasFuturas());
		
		return "/admin/reservas";		
	}
	/**
	 * Muestra las reservas pasadas en la ventana del administrador
	 * @param model
	 * @return
	 */
	@GetMapping("/reservas/historico")
	public String historicoReservas(Model model) {
		model.addAttribute("reservas", reservaservicio.buscarReservasPasadas());
		
		return "/admin/historicoReservas";
	}
	/**
	 * Elimina una reserva
	 * @param id
	 * @return
	 */
	@GetMapping("/eliminarReserva/{id}")
	public String eliminarReserva(@PathVariable("id") long id) {
		reservaservicio.deleteById(id);
		
		return "redirect:/admin/reservas";
	}
	
	
	
}
