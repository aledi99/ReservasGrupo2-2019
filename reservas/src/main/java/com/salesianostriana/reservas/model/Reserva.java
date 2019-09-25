package com.salesianostriana.reservas.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase que modela un evento o reserva de un Aula
 * @author Esperanza Macarena Escacena Morcillo
 *
 */
@Data @NoArgsConstructor
@Entity
@Table(name="Reserva")
public class Reserva {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private Horas hora;
	@ManyToOne
	private Aula aula;
	@ManyToOne
	private Usuario usuario;
	
	private LocalDate fecha;

	
	/**
	 * Constructor de una Reserva de un Aula por un usuario
	 * @param hora Hora de la reserva del aula
	 * @param aula Aula reservada
	 * @param usuario Usuario que realiza la reserva
	 * @param fecha Fecha de la reserva
	 * 
	 */
	public Reserva(Horas hora, Aula aula, Usuario usuario, LocalDate fecha) {
		super();
		this.hora = hora;
		this.aula = aula;
		this.usuario = usuario;
		this.fecha = fecha;
		
		
		
	}

	
	
	
	
}
