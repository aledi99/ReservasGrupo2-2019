package com.salesianostriana.reservas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Service;
import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.model.Horas;
import com.salesianostriana.reservas.model.Reserva;
import com.salesianostriana.reservas.repository.ReservaRepository;
@Service
public class ReservaServicio extends ServicioBase<Reserva, Long, ReservaRepository>{
	
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

	public List<Reserva> listarReservasCalendario(Horas h,List<LocalDate>fechaSemana,Aula aula) {
		List<Reserva> reservas=new ArrayList<Reserva>();
		for(LocalDate f: fechaSemana) {
			if(repositorio.findByFechaAndHoraAndAula(f, h,aula)!=null) {
				reservas.add(repositorio.findByFechaAndHoraAndAula(f, h,aula));
			}else {	
				reservas.add(null);
			}
		}
		return reservas;
	}
	public List<Horas> listarHorasLibres(LocalDate fecha, Aula aula){
		List <Horas> horasLibres= new ArrayList<>();
		Arrays.asList(Horas.values()).forEach(horasLibres::add);
		repositorio.findByFechaAndAula(fecha,aula)
			.stream()
			.forEach(x -> horasLibres.removeIf(p -> p.name().equals(x.getHora().name())));
		return horasLibres;
	}
	
	
	
}
