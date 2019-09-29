package com.salesianostriana.reservas.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
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
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate fecha;
	/**
	 * Determina si el festivo en cuestión debería aparecer en la lista de la pantalla de gestión de admin o no.
	 * Es necesario porque si no estuviera y los sábados y/o los domingos estuvieran marcados como festivos, la lista
	 * sería demasiado larga inútilmente.
	 */
	@NotNull
	private boolean listar;
	
	
}
