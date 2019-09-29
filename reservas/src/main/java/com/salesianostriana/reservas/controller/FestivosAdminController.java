package com.salesianostriana.reservas.controller;

import java.time.LocalDate;
import java.util.List;
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

	FestivoServicio festivoServicio;
	ReservaServicio reservaServicio;
	boolean festivoYaExiste = false;
	boolean sabadosHabilitados = false;
	boolean domingosHabilitados = false;

	public FestivosAdminController(FestivoServicio festivoServicio, ReservaServicio reservaServicio) {
		this.festivoServicio = festivoServicio;
		this.reservaServicio = reservaServicio;
	}

	/**
	 * Método que controla la pantalla de gestión de festivos de admin. El if es por
	 * si el administrador intenta marcar como festivo un día que ya está en la base
	 * de datos marcado como festivo.
	 */
	@GetMapping("/festivos")
	public String festivos(Model model) {
		model.addAttribute("formbeanFecha", new FormbeanFecha());
		model.addAttribute("sabado", sabadosHabilitados);
		model.addAttribute("domingo", domingosHabilitados);
		model.addAttribute("festivos", festivoServicio.listarFestivosSinFinesDeSemana());

		if (festivoYaExiste) {
			model.addAttribute("errorFestivo", festivoYaExiste);
			festivoYaExiste = false;
		}

		return "admin/festivos";
	}

	/**
	 * Comprueba si el festivo que se está tratando de añadir existe ya en la base
	 * de datos. En caso afirmativo devuelve un error pero no detiene la ejecución
	 * del programa ni guarda la fecha en la tabla de festivos de la base de datos
	 * 
	 * @param ff Bean que le pasa al fecha al controlador desde la plantilla
	 *           Thymeleaf
	 */
	@PostMapping("/festivos/submit")
	public String anadirDiaFestivo(@ModelAttribute("formbeanFecha") FormbeanFecha ff, Model model) {
		LocalDate date = ff.getFecha();

		if (festivoServicio.comprobarFestivo(date)) {
			festivoYaExiste = true;
		} else {
			Festivo anadir = new Festivo();
			anadir.setFecha(date);
			anadir.setListar(true);
			festivoServicio.save(anadir);

		}
		return "redirect:/admin/festivos";

	}

	/**
	 * Primero, determina con un boolean que los sabados están deshabilitados para
	 * que en la plantilla de Thymeleaf aparezca el botón de habilitarlos en vez del
	 * de deshabilitarlos. Después, guarda todos los sábados de este año y el
	 * siguiente en la tabla de festivos. No guardamos los sábados de más años
	 * porque en algún punto había que parar para no hacer el tiempo de ejecución de
	 * este método infinito y con dos años nos parecía suficiente. Si intenta añadir
	 * un sábado que ya estaba marcado como festivo no lo guarda para evitar la
	 * redundancia en la bbdd.
	 */
	@GetMapping("/deshabilitarSabados")
	public String deshabilitarSabados() {

		sabadosHabilitados = false;

		List<LocalDate> sabados = festivoServicio.BuscarSabados();

		for (int i = 0; i < sabados.size(); i++) {
			if (!festivoServicio.comprobarFestivo(sabados.get(i))) {
				Festivo anadir = new Festivo();
				anadir.setFecha(sabados.get(i));
				anadir.setListar(false);
				festivoServicio.save(anadir);
			}
		}

		return "redirect:/admin/festivos";
	}

	/**
	 * Primero, determina con un boolean que los domingos están deshabilitados para
	 * que en la plantilla de Thymeleaf aparezca el botón de habilitarlos en vez del
	 * de deshabilitarlos. Después, guarda todos los domingos de este año y el
	 * siguiente en la tabla de festivos. No guardamos los domingos de más años
	 * porque en algún punto había que parar para no hacer el tiempo de ejecución de
	 * este método infinito y con dos años nos parecía suficiente. Si intenta añadir
	 * un domingo que ya estaba marcado como festivo no lo guarda para evitar la
	 * redundancia en la bbdd.
	 */
	@GetMapping("/deshabilitarDomingos")
	public String deshabilitarDomingos() {

		domingosHabilitados = false;

		List<LocalDate> domingos = festivoServicio.BuscarDomingos();

		for (int i = 0; i < domingos.size(); i++) {
			if (!festivoServicio.comprobarFestivo(domingos.get(i))) {
				Festivo anadir = new Festivo();
				anadir.setFecha(domingos.get(i));
				anadir.setListar(false);
				festivoServicio.save(anadir);
			}
		}
		return "redirect:/admin/festivos";
	}

	/**
	 * Primero, determina con un boolean que los sábados están habilitados para que
	 * en la plantilla de Thymeleaf aparezca el botón de deshabilitarlos en vez del
	 * de habilitarlos. Después, borra todos los sábados de la tabla de festivos de
	 * la bbdd. En caso de haber introducido un sábado de manera manual en el
	 * calendario se entenderá que es un sábado especial y ha de seguir siendo
	 * festivo, por lo que no se borrará.
	 */
	@GetMapping("/habilitarSabados")
	public String habilitarSabados() {

		sabadosHabilitados = true;

		List<Festivo> festivos = festivoServicio.findAll();

		for (int i = 0; i < festivos.size(); i++) {
			if (festivos.get(i).getFecha().getDayOfWeek().getValue() == 6 && !festivos.get(i).isListar()) {
				festivoServicio.delete(festivos.get(i));
			}
		}

		return "redirect:/admin/festivos";
	}

	/**
	 * Primero, determina con un boolean que los domingos están habilitados para que
	 * en la plantilla de Thymeleaf aparezca el botón de deshabilitarlos en vez del
	 * de habilitarlos. Después, borra todos los domingos de la tabla de festivos de
	 * la bbdd. En caso de haber introducido un domingo de manera manual en el
	 * calendario se entenderá que es un domingo especial y ha de seguir siendo
	 * festivo, por lo que no se borrará.
	 */
	@GetMapping("/habilitarDomingos")
	public String habilitarDomingos() {

		domingosHabilitados = true;

		List<Festivo> festivos = festivoServicio.findAll();

		for (int i = 0; i < festivos.size(); i++) {
			if (festivos.get(i).getFecha().getDayOfWeek().getValue() == 7 && !festivos.get(i).isListar()) {
				festivoServicio.delete(festivos.get(i));
			}
		}

		return "redirect:/admin/festivos";
	}

}
