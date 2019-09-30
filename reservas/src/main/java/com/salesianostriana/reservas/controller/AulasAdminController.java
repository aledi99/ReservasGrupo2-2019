package com.salesianostriana.reservas.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.model.Pager;
import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.service.AulaServicio;

/**
 * Esta clase gestiona las peticiones del administrador sobre la gestión de
 * aulas
 * 
 * @author Álvaro Márquez
 *
 */
@Controller
@RequestMapping("/admin")
public class AulasAdminController {

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

	private AulaServicio aulaservicio;

	public AulasAdminController(AulaServicio aulaservicio) {
		this.aulaservicio = aulaservicio;

	}

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	@GetMapping("/espacios")
	public String espacios(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("nombre") Optional<String> nombre,
			Model model) {
		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
		// el tamaño de página inicial.
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

		// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
		// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
		// del parámetro decrementado en 1.
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Aula> espacios = null;

		espacios = aulaservicio.findAll(PageRequest.of(evalPage, evalPageSize));

		Pager pager = new Pager(espacios.getTotalPages(), espacios.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("espacios", espacios);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);

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

	@GetMapping("/espacios/eliminar/{id}")
	public String eliminarAula(@PathVariable("id") long id, Model model) {
		aulaservicio.eliminarAula(aulaservicio.findById(id));

		return "redirect:/admin/espacios";
	}

}
