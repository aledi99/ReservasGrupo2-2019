package com.salesianostriana.reservas.formbean;

import java.time.LocalDate;
import com.salesianostriana.reservas.model.Horas;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Clase de un formbean de reserva que contiene hora y fecha.
 * @author Esperanza M Escacena M
 *
 */
@Data @NoArgsConstructor
public class FormBeanReserva {
	private Horas hora;
	private LocalDate fecha;

}
