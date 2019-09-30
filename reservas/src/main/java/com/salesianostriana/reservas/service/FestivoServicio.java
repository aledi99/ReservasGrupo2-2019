package com.salesianostriana.reservas.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.reservas.model.Festivo;
import com.salesianostriana.reservas.repository.FestivoRepository;
/**
 * 
 * @author Álvaro Márquez
 *
 */
@Service
public class FestivoServicio extends ServicioBase<Festivo, Long, FestivoRepository> {

	/**
	 * Método que se ejecuta al arrancar en programa. Guarda todos los sábados y
	 * domingos de este año y del que viene en la tabla de festivos, para que no se
	 * puedan hacer reservas los sábados y domingos de manera predeterminada. El
	 * administrador podrá cambiar esto desde la pantalla de gestión de festivos.
	 */
	public void GuardarSabadosYDomingos() {
		LocalDate hoy = LocalDate.now();
		int anno = hoy.getYear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate startDate = LocalDate.parse("01/01/" + anno, formatter);
		LocalDate finishDate = LocalDate.parse("31/12/" + (anno + 1), formatter);

		for (LocalDate date = startDate; date.isBefore(finishDate); date = date.plusDays(1)) {
			if (date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7) {
				Festivo anadir = new Festivo();
				anadir.setFecha(date);
				repositorio.save(anadir);
			}
		}
	}

	/**
	 * Método que devuelve una lista de todos los sábados y domingos de el año
	 * actual y el siguiente
	 * 
	 * @return Lista de todos los sábados y domingos de el año actual y el siguiente
	 */
	public List<LocalDate> BuscarSabadosYDomingos() {
		LocalDate hoy = LocalDate.now();
		int anno = hoy.getYear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate startDate = LocalDate.parse("01/01/" + anno, formatter);
		LocalDate finishDate = LocalDate.parse("31/12/" + (anno + 1), formatter);

		List<LocalDate> festivos = new ArrayList<LocalDate>();

		for (LocalDate date = startDate; date.isBefore(finishDate); date = date.plusDays(1)) {
			if (date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7) {
				festivos.add(date);
			}
		}

		return festivos;
	}

	/**
	 * Método que devuelve una lista de todos los sábados de el año actual y el
	 * siguiente
	 * 
	 * @return Lista de todos los sábados de el año actual y el siguiente
	 */
	public List<LocalDate> BuscarSabados() {
		LocalDate hoy = LocalDate.now();
		int anno = hoy.getYear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate startDate = LocalDate.parse("01/01/" + anno, formatter);
		LocalDate finishDate = LocalDate.parse("31/12/" + (anno + 1), formatter);

		List<LocalDate> festivos = new ArrayList<LocalDate>();

		for (LocalDate date = startDate; date.isBefore(finishDate); date = date.plusDays(1)) {
			if (date.getDayOfWeek().getValue() == 6) {
				festivos.add(date);
			}
		}

		return festivos;
	}

	/**
	 * Método que devuelve una lista de todos los domingos de el año actual y el
	 * siguiente
	 * 
	 * @return Lista de todos los domingos de el año actual y el siguiente
	 */
	public List<LocalDate> BuscarDomingos() {
		LocalDate hoy = LocalDate.now();
		int anno = hoy.getYear();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate startDate = LocalDate.parse("01/01/" + anno, formatter);
		LocalDate finishDate = LocalDate.parse("31/12/" + (anno + 1), formatter);

		List<LocalDate> festivos = new ArrayList<LocalDate>();

		for (LocalDate date = startDate; date.isBefore(finishDate); date = date.plusDays(1)) {
			if (date.getDayOfWeek().getValue() == 7) {
				festivos.add(date);
			}
		}

		return festivos;
	}

	/**
	 * Método que se ejecuta al intentar añadir una fecha como día festivo. Busca si
	 * la fecha a guardar está ya en la tabla de festivos. Devuelve true si ya
	 * existe y false si no. Se utiliza para evitar que haya fechas repetidas en la
	 * tabla de festivos y devolver un mensaje de error en caso de que se intente
	 * guardar una fecha repetida
	 * 
	 * @return booleano. True si la fecha a guardar ya existe en la tabla festivos,
	 *         false si no existe.
	 */
	public boolean comprobarFestivo(LocalDate fecha) {
		boolean encontrado = false;
		List<Festivo> festivos = repositorio.findAll();
		boolean salir = false;
		for (int i = 0; i < festivos.size() && !salir; i++) {
			if (festivos.get(i).getFecha().equals(fecha)) {
				salir = true;
				encontrado = true;
			}
		}

		return encontrado;
	}

	/**
	 * Método que devuelve una lista de los festivos añadidos manualmente por el
	 * administrador. Se utiliza para imprimir la lista en la pantalla de gestión de
	 * festivos. No se incluyen los sábados y domingos que se hayan añadido mediante
	 * los métodos de añadir todos los sábados y/o domingos para evitar que la lista
	 * sea demasiado larga innecesariamente.
	 * 
	 * @return Lista de todos los festivos añadidos manualmente por el
	 *         administrador.
	 */
	public List<Festivo> listarFestivosSinFinesDeSemana() {
		List<Festivo> festivos = new ArrayList<Festivo>();
		LocalDate hoy = LocalDate.now();

		for (Festivo f : repositorio.findAll()) {
			if (f.isListar() && hoy.getYear() <= f.getFecha().getYear()) {
				festivos.add(f);
			}
		}

		return festivos;
	}

}
