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
import com.salesianostriana.reservas.formbean.FormBeanReserva;
import com.salesianostriana.reservas.formbean.FormbeanFecha;
import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.model.Reserva;
import com.salesianostriana.reservas.service.AulaServicio;
import com.salesianostriana.reservas.service.FestivoServicio;
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
	@Autowired
	FestivoServicio fs;
	private boolean esFestivo;
	private boolean errReservaImposible;
	/**
	 * Muestra la pantalla para escoger una fecha y luego mostrar la semana.
	 * El modelo aula indica que aula se va a alquilar, la cual se busca mediante un id.
	 * El modelo formbeanFecha recoge la fecha que va a buscar en el calendario el usuario y se va a setear en la nueva reserva.
	 * El modelo horas corresponde a la lista de horas.
	 * Los modelos reserva1-reserva6 son listas de reservas semanales por hora, como son 6 horas, hay 6 listas. Así podremos reflejar las
	 * reservas en una tabla.
	 * El modelo semana es el intervalo de días.
	 * nuevaReserva es la reserva que se va a realizar.
	 * 
	 * También se pasa los modelos esFestivo (para lanzar un alert de que no puede reservar un día festivo) 
	 * y reservaImposible (para lanzar un aviso de que no puede estar en dos sitios a la vez)
	 * @param model
	 * @param p
	 * @return
	 */
	@GetMapping("/user/reservar/{id}")
	public String mostrarReservar(@PathVariable("id") long id,Model model, Principal p) {
		LocalDate fecha=LocalDate.now();
		FormBeanReserva fbr=new FormBeanReserva();
		if(esFestivo) {
			model.addAttribute("esFestivo", esFestivo);
			esFestivo=false;
		}
		if(errReservaImposible) {
			model.addAttribute("reservaImposible", errReservaImposible);
			errReservaImposible=false;
		}
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
		model.addAttribute("nuevaReserva",fbr);

		return "user/reservas";
	}
	/**
	 * Muestra la semana de la fecha escogida y los eventos.
	 * Este método también hace una comprobación de si la fecha es festivo o no. 
	 * En caso de que la fecha escogida sea sábado, dómingo o festivo, la variable
	 * booleana se setea a TRUE (es festivo) y se redirige a la anterior ventana
	 * y además mostrará un alert (en la ventana anterior).
	 * 
	 * Además hay un objeto formbeanfecha para poder setear la fecha elegida
	 * así no hay problemas al setear hora y fecha, ya que son diferentes formularios.
	 * @param ff fecha escogida por el usuario por el calendario
	 * @param model
	 * @return
	 */
	@PostMapping("/user/reservar/{id}/submit")
	public String mostrarReservarSemana(@PathVariable("id") long id,
			@ModelAttribute("formbeanFecha") FormbeanFecha ff, Model model, Principal p) {
		Aula aula=as.findById(id);
		FormBeanReserva fbr=new FormBeanReserva();
		LocalDate fecha=ff.getFecha();
		fbr.setFecha(fecha);
		//No me salía lo de JavaScript...estoy muy verde. Será para la Espe del futuro cuando Miguel explique JS.
		if(fs.comprobarFestivo(fecha)) {
			esFestivo=true;
			return "redirect:/user/reservar/{id}";
		}else {
			esFestivo=false;
		}
		
	
		
		model.addAttribute("aula",aula);	
		model.addAttribute("horasLibres", rs.listarHorasLibres(fecha, aula));
		model.addAttribute("horas",Horas.values());
		model.addAttribute("reserva1", rs.listarReservasCalendario(Horas.PRIMERA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva2", rs.listarReservasCalendario(Horas.SEGUNDA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva3", rs.listarReservasCalendario(Horas.TERCERA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva4", rs.listarReservasCalendario(Horas.CUARTA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva5", rs.listarReservasCalendario(Horas.QUINTA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("reserva6", rs.listarReservasCalendario(Horas.SEXTA,rs.CalcularSemanasMes(fecha),aula));
		model.addAttribute("semana",rs.CalcularSemanasMes(fecha));
		model.addAttribute("usuario",us.buscarUsuarioLogged(p));
		model.addAttribute("nuevaReserva",fbr);
		
		return "user/reservas";
	}
	/**
	 * Una vez elegida la hora y la fecha se crea una nueva reserva. 
	 * Si existe una reserva de una diferente aula pero misma fecha y hora para un usuario dado
	 * no se crea reserva, se redirige al inicio del calendario y se le avisa al usuario de 
	 * que tiene una reserva a la misma hora y fecha en otra aula.
	 * @param id id del aula para poder buscarla y setearla en la Reserva
	 * @param fbr FormBeanReserva que contiene la fecha y hora de la Reserva
	 * @param model
	 * @param p Principal, con esto conseguiremos el usuario que realiza la reserva
	 * @return
	 */
	@PostMapping("/user/reservar/{id}/nueva-reserva/submit")
	public String crearNuevaReserva(@PathVariable("id") long id,
			@ModelAttribute("nuevaReserva") FormBeanReserva fbr,
			Model model, Principal p) {
			Reserva r= new Reserva();
			if(rs.comprobarReservaImposible(us.buscarUsuarioLogged(p),
					fbr.getFecha(), fbr.getHora())) {
				errReservaImposible=true;
				return "redirect:/user/reservar/{id}";
			}else {
				errReservaImposible=false;
			}
			r.setAula(as.findById(id));
			r.setUsuario(us.buscarUsuarioLogged(p));
			r.setFecha(fbr.getFecha());
			r.setHora(fbr.getHora());
			
			rs.save(r);
			
		return "redirect:/user/reservar/{id}";
	}
	
	
}
