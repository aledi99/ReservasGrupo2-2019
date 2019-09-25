package com.salesianostriana.reservas.formbean;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class FormbeanFecha {
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha;
}
