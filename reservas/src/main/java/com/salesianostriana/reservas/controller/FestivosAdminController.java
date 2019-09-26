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
	boolean festivoYaExiste = false;

	public FestivosAdminController(FestivoServicio festivoServicio, ReservaServicio reservaServicio) {
		this.festivoServicio = festivoServicio;
		this.reservaServicio = reservaServicio;
	}

	@GetMapping("/festivos")
	public String festivos(Model model) {
		model.addAttribute("formbeanFecha", new FormbeanFecha());

		if (festivoYaExiste) {
			model.addAttribute("errorFestivo", festivoYaExiste);
			festivoYaExiste = false;
		}

		return "admin/festivos";
	}

	@PostMapping("/festivos/submit")
	public String anadirDiaFestivo(@ModelAttribute("formbeanFecha") FormbeanFecha ff, Model model) {
		LocalDate date = ff.getFecha();

		if (festivoServicio.comprobarFestivo(date)) {
			festivoYaExiste = true;
		} else {
			Festivo anadir = new Festivo();
			anadir.setFecha(date);
			festivoServicio.save(anadir);

		}
		return "redirect:/admin/festivos";

	}

}
