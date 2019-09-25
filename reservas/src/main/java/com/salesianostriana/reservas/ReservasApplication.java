package com.salesianostriana.reservas;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.service.UsuarioServicio;

@SpringBootApplication
public class ReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
		
		
	}
	@Bean
	public CommandLineRunner init(UsuarioServicio us, BCryptPasswordEncoder encriptar) {
		return args -> {
			
			
			for(Usuario usu: us.findAll()) {
				usu.setPassword(encriptar.encode(usu.getPassword()));
				us.save(usu);
				System.out.println(usu);
			}
			
			LocalDate hoy = LocalDate.now();
			int anno = hoy.getYear();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate startDate = LocalDate.parse("01/01/" + anno, formatter);
			LocalDate finishDate = LocalDate.parse("31/12/" + (anno + 1), formatter);
			
			List<LocalDate> festivos = new ArrayList<LocalDate>();
			
			for(LocalDate date = startDate ; date.isBefore(finishDate) ; date = date.plusDays(1)) {
				if (date.getDayOfWeek().getValue() == 6 || date.getDayOfWeek().getValue() == 7) {
					festivos.add(date);
				}
			} 
			
			
		};
	}

}
