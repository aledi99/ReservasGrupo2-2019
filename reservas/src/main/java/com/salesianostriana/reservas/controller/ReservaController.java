package com.salesianostriana.reservas.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.reservas.formbean.FormbeanFecha;
import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.service.ReservaServicio;




@Controller
public class ReservaController {
	@Autowired
	ReservaServicio rs;
	/**
	 * Muestra la pantalla para escoger una fecha y luego mostrar la semana
	 * @param model
	 * @param p
	 * @return
	 */
	@GetMapping("/user/reservar")
	public String mostrarReservar(Model model, Principal p) {
		
		model.addAttribute("formbeanFecha",new FormbeanFecha());
		model.addAttribute("p", p);
		return "user/reservas";
	}
	/**
	 * Muestra la semana de la fecha escogida y los eventos.
	 * @param ff
	 * @param model
	 * @return
	 */
	@PostMapping("/user/reservar/semana")
	public String mostrarReservarSemana(@ModelAttribute("formbeanFecha") FormbeanFecha ff,Model model) {
		LocalDate fecha=rs.ConversorTextoFecha(ff.getFecha());
		model.addAttribute("horas",Horas.values());
		model.addAttribute("reservas", rs.listarReservasSemana(rs.CalcularSemanasMes(fecha)));
		model.addAttribute("semana",rs.CalcularSemanasMes(fecha));
		
		return "user/reservas";
	}
	
	
	
}
