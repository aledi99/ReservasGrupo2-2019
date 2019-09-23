package com.salesianostriana.reservas.model;

import java.time.LocalDate;


import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Embeddable
public class Festivo {
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha;
	private boolean festivo;
	
	
}
