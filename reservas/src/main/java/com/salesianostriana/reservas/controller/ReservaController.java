package com.salesianostriana.reservas.controller;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.reservas.formbean.FormbeanFecha;
import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.service.AulaServicio;
import com.salesianostriana.reservas.service.ReservaServicio;
import com.salesianostriana.reservas.service.UsuarioServicio;




@Controller
public class ReservaController {
	@Autowired
	ReservaServicio rs;
	@Autowired
	AulaServicio as;
	@Autowired
	UsuarioServicio us;
	/**
	 * Muestra la pantalla para escoger una fecha y luego mostrar la semana
	 * @param model
	 * @param p
	 * @return
	 */
	@GetMapping("/user/reservar/{id}")
	public String mostrarReservar(@PathVariable("id") long id,Model model, Principal p) {
		LocalDate fecha=LocalDate.now();
		Aula aula=as.findById(id);
		model.addAttribute("aula",aula);
		model.addAttribute("formbeanFecha",new FormbeanFecha());
		model.addAttribute("usuario",us.buscarUsuarioLogged(p));
		model.addAttribute("horas",Horas.values());
		model.addAttribute("reserva1", rs.listarReservasCalendario(Horas.PRIMERA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva2", rs.listarReservasCalendario(Horas.SEGUNDA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva3", rs.listarReservasCalendario(Horas.TERCERA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva4", rs.listarReservasCalendario(Horas.CUARTA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva5", rs.listarReservasCalendario(Horas.QUINTA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva6", rs.listarReservasCalendario(Horas.SEXTA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("semana",rs.CalcularSemanasMes(fecha));
		return "user/reservas";
	}
	/**
	 * Muestra la semana de la fecha escogida y los eventos.
	 * @param ff
	 * @param model
	 * @return
	 */
	@PostMapping("/user/reservar/{id}/submit")
	public String mostrarReservarSemana(@PathVariable("id") long id,
			@ModelAttribute("formbeanFecha") FormbeanFecha ff, Model model, Principal p) {
		Aula aula=as.findById(id);
		LocalDate fecha=rs.ConversorTextoFecha(ff.getFecha());
		model.addAttribute("aula",aula);	
		model.addAttribute("horas",Horas.values());
		model.addAttribute("reserva1", rs.listarReservasCalendario(Horas.PRIMERA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva2", rs.listarReservasCalendario(Horas.SEGUNDA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva3", rs.listarReservasCalendario(Horas.TERCERA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva4", rs.listarReservasCalendario(Horas.CUARTA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva5", rs.listarReservasCalendario(Horas.QUINTA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva6", rs.listarReservasCalendario(Horas.SEXTA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("semana",rs.CalcularSemanasMes(fecha));
		model.addAttribute("usuario",us.buscarUsuarioLogged(p));
		
		return "user/reservas";
	}
	
	
	
}
