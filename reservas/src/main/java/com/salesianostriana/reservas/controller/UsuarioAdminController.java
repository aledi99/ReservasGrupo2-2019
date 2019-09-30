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

import com.salesianostriana.reservas.model.Pager;
import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.service.UsuarioServicio;

/**
 * Esta clase gestiona las peticiones del administrador sobre la gestión del
 * usuario
 * 
 * @author Álvaro Márquez
 *
 */
@Controller
@RequestMapping("/admin")
public class UsuarioAdminController {

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

	private UsuarioServicio usuarioservicio;

	public UsuarioAdminController(UsuarioServicio usuarioservicio) {
		this.usuarioservicio = usuarioservicio;
	}

	private static final int BUTTONS_TO_SHOW = 5;
	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 5, 10, 20 };

	@ModelAttribute("usuarios")
	public void listaSolicitudes(Model model) {
		model.addAttribute("usuarios", usuarioservicio.buscarPorGestionadoFalse());
	}

	/**
	 * Muestra la plantilla donde se muestra las solicitudes de usuarios (no
	 * gestionados)
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/solicitudes")
	public String solicitudes(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("nombre") Optional<String> nombre,
			Model model) {

		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
		// el tamaño de página inicial.
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

		// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
		// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
		// del parámetro decrementado en 1.
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Usuario> usuarios = null;

		usuarios = usuarioservicio.buscarPorGestionadoFalse(PageRequest.of(evalPage, evalPageSize));

		Pager pager = new Pager(usuarios.getTotalPages(), usuarios.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("usuarios", usuarios);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);
		return "admin/solicitudes";

	}

	/**
	 * Elimina la solicitud de un usuario
	 * 
	 * @param id    ID del usuario a eliminar
	 * @param model
	 * @return Redirige a la página principal de solicitudes
	 */
	@GetMapping("/eliminarSolicitud/{id}")
	public String eliminarSolicitud(@PathVariable("id") long id, Model model) {
		usuarioservicio.deleteById(id);

		model.addAttribute("usuarios");
		return "redirect:/admin/solicitudes";
	}

	/**
	 * Valida una solicitud
	 * 
	 * @param id    ID del usuario a eliminar
	 * @param model
	 * @return Redirige a la página principal de solicitudes
	 */
	@PostMapping("/validar/{id}")
	public String validarUsuario(@PathVariable("id") long id, Model model) {
		Usuario usuario = usuarioservicio.findById(id);
		usuario.setGestionado(true);
		usuarioservicio.edit(usuario);

		model.addAttribute("usuarios");
		return "redirect:/admin/solicitudes";
	}

	/**
	 * Muestra la plantilla de usuarios
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping("/usuarios")
	public String usuarios(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("nombre") Optional<String> nombre,
			Model model) {
		// Evalúa el tamaño de página. Si el parámetro es "nulo", devuelve
		// el tamaño de página inicial.
		int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

		// Calcula qué página se va a mostrar. Si el parámetro es "nulo" o menor
		// que 0, se devuelve el valor inicial. De otro modo, se devuelve el valor
		// del parámetro decrementado en 1.
		int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

		Page<Usuario> usuarios = null;

		usuarios = usuarioservicio.buscarPorGestionadoTrueAndAdminFalse(PageRequest.of(evalPage, evalPageSize));

		Pager pager = new Pager(usuarios.getTotalPages(), usuarios.getNumber(), BUTTONS_TO_SHOW);

		model.addAttribute("usuarios", usuarios);
		model.addAttribute("selectedPageSize", evalPageSize);
		model.addAttribute("pageSizes", PAGE_SIZES);
		model.addAttribute("pager", pager);
		return "admin/usuarios";
	}

}
