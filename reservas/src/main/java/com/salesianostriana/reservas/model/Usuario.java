package com.salesianostriana.reservas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase POJO de Usuario.
 * 
 * @author Esperanza Macarena Escacena Morcillo
 *
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(unique = true)
	private String email;
	private String password;
	private String nombre;
	private String apellidos;
	private boolean admin;
	private boolean gestionado;

	/**
	 * Constructo de un Usuario. Podrá ser usuario o administrador que será
	 * determinado por un booleano.
	 * 
	 * @param email     Funciona como nombre de usuario.
	 * @param password  Contraseña de la cuenta
	 * @param nombre    Nombre del usuario
	 * @param apellidos Apellidos del usuario
	 * @param admin     Indica si el usuario es administrador o no
	 */
	public Usuario(String email, String password, String nombre, String apellidos, boolean admin) {

		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.admin = admin;
		this.gestionado = false;
	}

}
