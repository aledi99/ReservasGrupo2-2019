package com.salesianostriana.reservas.service;
/**
 * @author Esperanza M Escacena M
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.model.Reserva;
import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.repository.ReservaRepository;
@Service
public class ReservaServicio extends ServicioBase<Reserva, Long, ReservaRepository>{
	/**
	 * Método para calcular un intervalo de una semana para poder imprimirlo en un calendario semanal.
	 * 
	 * @param fecha fecha seleccionada por el usuario.
	 * @return lista de fechas de una semana
	 */
	public List<LocalDate> CalcularSemanasMes(LocalDate fecha) {
		List<LocalDate> fechaSemana= new ArrayList<LocalDate>();
		int dia=fecha.getDayOfMonth();
		int diasAnteriores=0;
		
		switch(fecha.getDayOfWeek().getValue()) {
			case 1:
				for(int i=0;i<7;i++) {
					if(dia<=fecha.lengthOfMonth()) {
						fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),dia));
						dia++;
					}else {
						fechaSemana.add(null);
					}
				
				}
				break;
			case 2:				
				diasAnteriores=dia-1;
				if(diasAnteriores>0){
					fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
				}else {
					fechaSemana.add(null);					
				}
				
				for(int i=0;i<6;i++) {
					if(dia<=fecha.lengthOfMonth()) {
					fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),dia));
					dia++;
					}else {
						fechaSemana.add(null);
					}			
				}
				break;
			case 3:
				diasAnteriores=dia-2;
				for(int i=0;i<2;i++) {
					if(diasAnteriores>0&&diasAnteriores<dia) {
						fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
						diasAnteriores++;
					}else {
						fechaSemana.add(null);
						if(diasAnteriores<=0) {
							diasAnteriores++;
						}
					}
				}
				for(int i=0;i<5;i++) {
					if(dia<=fecha.lengthOfMonth()) {
						fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),dia));
						dia++;
					}else {
						fechaSemana.add(null);
					}
				}
				break;
			case 4:				
				diasAnteriores=dia-3;
				for(int i=0;i<3;i++) {
					if(diasAnteriores>0&&diasAnteriores<dia) {
						fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
						diasAnteriores++;
					}else {
						fechaSemana.add(null);
						if(diasAnteriores<=0) {
							diasAnteriores++;
						}
					}
				}
				for(int i=0;i<4;i++) {
					if(dia<=fecha.lengthOfMonth()) {
						fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),dia));
						dia++;
					}else {
						fechaSemana.add(null);
					}
				}
				break;
			case 5:
				diasAnteriores=dia-4;				
				for(int i=0;i<4;i++) {					
					if(diasAnteriores>0&&diasAnteriores<dia) {
						fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
						diasAnteriores++;
					}else {
						fechaSemana.add(null);
						if(diasAnteriores<=0) {
							diasAnteriores++;
						}
					}
				}
				for(int i=0;i<3;i++) {
					if(dia<=fecha.lengthOfMonth()) {
						fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),dia));
						dia++;
					}else {
						fechaSemana.add(null);
					}
				}
				break;
			case 6:			
				diasAnteriores=dia-5;
				for(int i=0;i<5;i++) {
					if(diasAnteriores>0&&diasAnteriores<dia) {
						fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
						diasAnteriores++;
					}else {
						fechaSemana.add(null);
						if(diasAnteriores<=0) {
							diasAnteriores++;
						}
					}
				}
				for(int i=0;i<2;i++) {
					if(dia<=fecha.lengthOfMonth()) {
						fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),dia));
						dia++;
					}else {
						fechaSemana.add(null);
					}
				}
				break;
			case 7:			
				diasAnteriores=dia-6;
				for(int i=0;i<6;i++) {
					if(diasAnteriores>0&&diasAnteriores<dia) {
						fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
						diasAnteriores++;
					}else {
						fechaSemana.add(null);
						if(diasAnteriores<=0) {
							diasAnteriores++;
						}
					}
				}				
				if(dia<=fecha.lengthOfMonth()) {
					fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),dia));
					
				}else {
					fechaSemana.add(null);
				}			
				break;
		}
	
		return fechaSemana;
	}
	/**
	 * Método para listar las reservas de la semana de un Aula dada
	 * @param h Enum Horas.
	 * @param fechaSemana Lista de fechas de una semana, de lunes a domingo
	 * @param aula Aula de la que se va a mirar las reservas
	 * @return lista de reservas de un aula en una semana determinada.
	 */
	public List<Reserva> listarReservasCalendario(Horas h,List<LocalDate>fechaSemana,Aula aula) {
		List<Reserva> reservas=new ArrayList<Reserva>();
		if(aula!=null) {
			for(LocalDate f: fechaSemana) {
				
				if(repositorio.findByFechaAndHoraAndAula(f, h,aula)!=null) {
					reservas.add(repositorio.findByFechaAndHoraAndAula(f, h,aula));
				}else {	
					reservas.add(null);
				}
			}
		}
		
		return reservas;
	}
	/**
	 * Método para listar las horas libres. Para ello se comparara con una lista de reservas de la fecha seleccionada
	 * por el usuario. Todas aquellas horas que estén ya recogidas en una reserva en una fecha y aula dada, se eliminará de la lista.
	 * @param fecha Fecha seleccionada por el usuario
	 * @param aula Aula que quiere reservar el usuario
	 * @return Lista de horas libres.
	 */
	
	public List<Horas> listarHorasLibres(LocalDate fecha, Aula aula){
		List <Horas> horasLibres= new ArrayList<>();
		Arrays.asList(Horas.values()).forEach(horasLibres::add);
		repositorio.findByFechaAndAula(fecha,aula)
			.stream()
			.forEach(x -> horasLibres.removeIf(p -> p.name().equals(x.getHora().name())));
		return horasLibres;
	}
	
	/**
	 * Este método es para comprobar si el usuario tiene una reserva en un aula con la misma fecha y hora que 
	 * pretende reservar. Es imposible estar en dos sitios a la vez.
	 * Si es posible la reserva, devolverá false. Pero si es imposible, devuelve true.
	 * @return
	 */
	public boolean comprobarReservaImposible(Usuario usuario, LocalDate fecha, Horas hora) {
		return repositorio.findByUsuarioAndFechaAndHora(usuario, fecha, hora)==null? false:true;
	}
	/**
	 * Lista reservas de hoy en adelante.
	 * Hecho por Álvaro Márquez
	 * @return lista de reservas actuales y futuras.
	 */
	public List<Reserva> buscarReservasFuturas() {
		List <Reserva> FuturasReservas = new ArrayList<Reserva>();
		LocalDate hoy = LocalDate.now();
		
		for (Reserva r : repositorio.findAll()) {
			if (r.getFecha().isAfter(hoy) || r.getFecha().equals(hoy)) {
				FuturasReservas.add(r);
			}
		}
		
		return FuturasReservas;
		
	}
	/**
	 * Lista reservas pasadas
	 * Hecho por Álvaro Márquez
	 * @return lista de reservas pasadas
	 */
	public List<Reserva> buscarReservasPasadas() {
		List <Reserva> PasadasReservas = new ArrayList<Reserva>();
		LocalDate hoy = LocalDate.now();
		
		for (Reserva r : repositorio.findAll()) {
			if (r.getFecha().isBefore(hoy)) {
				PasadasReservas.add(r);
			}
		}
		
		return PasadasReservas;
	}
	/**
	 * Método para eliminar reservas de un aula dada. Será invocado cuando
	 * el administrador quiera eliminar un aula
	 * @param aula aula de la que se quiere eliminar sus reservas.
	 */
	public void eliminarReservasPorAula(Aula aula) {
		if(repositorio.findByAula(aula)!=null){
			repositorio.deleteAll(repositorio.findByAula(aula));
		}
	}
	/**
	 * Lista las reservas por usuarios
	 * @param usuario
	 * @return lista de reservas filtrada
	 */
	public List<Reserva> listarReservasPorUsuario(Usuario usuario){
		return repositorio.findByUsuario(usuario);
	}
}
