package com.salesianostriana.reservas.service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
	public LocalDate ConversorTextoFecha(String fecha) {
		String date=fecha;
		System.out.println("prueba"+fecha +"lenght"+fecha.length());
		if(fecha.length()>10) {
			fecha=date.substring(0, 10);
			System.out.println("prueba"+fecha);
			
		}
		DateTimeFormatter formato; 
		LocalDate f;
		formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    f = LocalDate.parse(fecha, formato);
	    f.format(formato);	 
		return f;
	}
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
			fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),dia-1));
			
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
				fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
				diasAnteriores++;
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
				fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
				diasAnteriores++;
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
				fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
				diasAnteriores++;
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
				fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
				diasAnteriores++;
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
				fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),diasAnteriores));
				diasAnteriores++;
			}
			
			if(dia<=fecha.lengthOfMonth()) {
				fechaSemana.add(LocalDate.of(fecha.getYear(), fecha.getMonth().getValue(),dia));
				
			}else {
				fechaSemana.add(null);
			}
				
			
			break;
		}
		for(LocalDate i:fechaSemana) {
			System.out.println(i);
		}
	
		return fechaSemana;
	}

	public List<Reserva> listarReservasCalendario(Horas h,List<LocalDate>fechaSemana,Aula aula) {
		List<Reserva> reservas=new ArrayList<Reserva>();

		for(LocalDate f: fechaSemana) {
			if(repositorio.findByFechaAndHoraAndAula(f, h,aula)!=null) {
				reservas.add(repositorio.findByFechaAndHoraAndAula(f, h,aula));
			}else {
				
				reservas.add(null);
			}
			
		}
		for(Reserva i: reservas) {
			System.out.println(i);
		}
		return reservas;

	}
	public List<Horas> listarHorasLibres(LocalDate fecha, Aula aula){
		System.out.println("fecha en listarhoraslibres"+fecha);
		System.out.println("aula en listarhoraslibres"+aula);
	//	List <Reserva> r=repositorio.findByFechaAndAula(fecha,aula);
		List <Horas> horasLibres= new ArrayList<>();
		Arrays.asList(Horas.values()).forEach(horasLibres::add);
		System.out.println("CREADO" +horasLibres);
		for(Horas i : Horas.values()) {
			System.out.println("HORA BUCLE"+i);
			for(Reserva j: repositorio.findByFechaAndAula(fecha,aula)) {	
				if(j.getHora()==i) {
					
					horasLibres.removeIf(p -> p.name().equals(j.getHora().name()));
					System.out.println("ELIMINADO" +horasLibres);
					
					
				}
			}
		}
		horasLibres.stream().forEach(x -> System.out.println(x));
		return horasLibres;
	}
	
	
	
}
