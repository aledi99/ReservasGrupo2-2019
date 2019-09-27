package com.salesianostriana.reservas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesianostriana.reservas.model.Aula;
import com.salesianostriana.reservas.repository.AulaRepository;
/**
 * Clase de servicio de Aula
 * @author Esperanza M Escacena M
 *
 */
@Service
public class AulaServicio extends ServicioBase<Aula, Long, AulaRepository> {
	@Autowired
	ReservaServicio rs;
	/**
	 * Para eliminar aula, primero eliminamos todas las reservas.
	 * @param aula Aula a eliminar
	 */
	public void eliminarAula(Aula aula) {
		rs.eliminarReservasPorAula(aula);
		repositorio.delete(aula);
	}
}
