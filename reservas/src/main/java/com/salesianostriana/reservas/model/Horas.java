package com.salesianostriana.reservas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public enum Horas {
	PRIMERA ("08:00"),
	SEGUNDA ("09:00"),
	TERCERA ("10:00"),
	CUARTA ("11.30"),
	QUINTA ("12.30"),
	SEXTA ("13.30");
	private String descripcion;
}
