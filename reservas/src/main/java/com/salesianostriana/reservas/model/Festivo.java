package com.salesianostriana.reservas.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Festivo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate fecha;
	private boolean festivo;
	
	
}
