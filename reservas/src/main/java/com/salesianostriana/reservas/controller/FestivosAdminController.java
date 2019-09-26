package com.salesianostriana.reservas.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
	boolean sabadosHabilitados = true;
	boolean domingosHabilitados = true;

	public FestivosAdminController(FestivoServicio festivoServicio, ReservaServicio reservaServicio) {
		this.festivoServicio = festivoServicio;
		this.reservaServicio = reservaServicio;
	}

	@GetMapping("/festivos")
	public String festivos(Model model) {
		model.addAttribute("formbeanFecha", new FormbeanFecha());
		model.addAttribute("sabado", sabadosHabilitados);
		model.addAttribute("domingo", domingosHabilitados);

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

	@GetMapping("/habilitarSabados")
	public String habilitarSabados() {

		sabadosHabilitados = true;

		List<LocalDate> sabados = festivoServicio.BuscarSabados();

		for (int i = 0; i < sabados.size(); i++) {

			Festivo anadir = new Festivo();
			anadir.setFecha(sabados.get(i));
			festivoServicio.save(anadir);
		}

		return "redirect:/admin/festivos";
	}

	@GetMapping("/habilitarDomingos")
	public String habilitarDomingos() {

		domingosHabilitados = true;

		List<LocalDate> domingos = festivoServicio.BuscarDomingos();

		for (int i = 0; i < domingos.size(); i++) {

			Festivo anadir = new Festivo();
			anadir.setFecha(domingos.get(i));
			festivoServicio.save(anadir);
		}

		return "redirect:/admin/festivos";
	}

	@GetMapping("/deshabilitarSabados")
	public String deshabilitarSabados() {

		sabadosHabilitados = false;

		List<Festivo> festivos = festivoServicio.findAll();

		for (int i = 0; i < festivos.size(); i++) {
			if (festivos.get(i).getFecha().getDayOfWeek().getValue() == 6) {
				festivoServicio.delete(festivos.get(i));
			}
		}

		return "redirect:/admin/festivos";
	}

	@GetMapping("/deshabilitarDomingos")
	public String deshabilitarDomingos() {
		
		domingosHabilitados = false;

		List<Festivo> festivos = festivoServicio.findAll();

		for (int i = 0; i < festivos.size(); i++) {
			if (festivos.get(i).getFecha().getDayOfWeek().getValue() == 7) {
				festivoServicio.delete(festivos.get(i));
			}
		}

		return "redirect:/admin/festivos";
	}

}
