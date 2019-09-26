package com.salesianostriana.reservas.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.reservas.model.Festivo;
import com.salesianostriana.reservas.repository.FestivoRepository;

@Service
public class FestivoServicio extends ServicioBase<Festivo, Long, FestivoRepository> {

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
	
	public boolean comprobarFestivo(LocalDate fecha) {
		boolean encontrado = false;
		List<Festivo> festivos = repositorio.findAll();
		boolean salir = false;
		for (int i = 0 ; i < festivos.size() && !salir; i++ ) {
			if (festivos.get(i).getFecha().equals(fecha)) {
				salir = true;
				encontrado = true;
			}
		}
		
		return encontrado;
	}
	
	
	
	
}
