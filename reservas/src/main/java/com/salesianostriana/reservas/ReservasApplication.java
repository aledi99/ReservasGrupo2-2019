package com.salesianostriana.reservas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.service.FestivoServicio;
import com.salesianostriana.reservas.service.UsuarioServicio;

@SpringBootApplication
public class ReservasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
		
		
	}
	
	@Profile("dev")
	@Bean
	public CommandLineRunner init(UsuarioServicio us, BCryptPasswordEncoder encriptar, FestivoServicio fs) {
		return args -> {
			
			
			for(Usuario usu: us.findAll()) {
				usu.setPassword(encriptar.encode(usu.getPassword()));
				us.save(usu);
				System.out.println(usu);
			}
			
			fs.GuardarSabadosYDomingos();
			
			
		};
	}

}
