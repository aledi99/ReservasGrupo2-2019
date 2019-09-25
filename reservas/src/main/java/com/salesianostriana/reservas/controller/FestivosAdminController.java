package com.salesianostriana.reservas.controller;

import java.time.LocalDate;
import java.util.function.Consumer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.salesianostriana.reservas.formbean.FormbeanFecha;
import com.salesianostriana.reservas.model.Festivo;
import com.salesianostriana.reservas.service.FestivoServicio;
import com.salesianostriana.reservas.service.ReservaServicio;

@Controller
@RequestMapping("/admin")
public class FestivosAdminController {
	
	FestivoServicio festivoServicio;
	ReservaServicio reservaServicio;
	
	public FestivosAdminController(FestivoServicio festivoServicio, ReservaServicio reservaServicio) {
		this.festivoServicio = festivoServicio;
		this.reservaServicio = reservaServicio;
	}

	@GetMapping("/festivos")
	public String festivos(Model model) {
		model.addAttribute("formbeanFecha", new FormbeanFecha());
		
		
		return "admin/festivos";
	}
	/**
	@PostMapping("/festivos/submit")
	public String anadirDiaFestivo(@ModelAttribute("formbeanFecha") FormbeanFecha ff, Model model) {
		LocalDate date = reservaServicio.ConversorTextoFecha(ff.getFecha());
		
	}
*/
}
