package com.salesianostriana.reservas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.salesianostriana.reservas.model.Usuario;
import com.salesianostriana.reservas.service.UsuarioServicio;
/**
 * Esta clase gestiona las peticiones sobre el registro de la página
 * @author Alejandro Díaz
 *
 */
@Controller
public class SignUpController {
	@Autowired
	UsuarioServicio s;
	
	private boolean errorEmailSignUp = true;
	/**
	 * Muestra la plantilla de registro
	 * @param model
	 * @return
	 */
	@GetMapping("/signup")
	public String getRegistro(Model model) {
		model.addAttribute("usuario", new Usuario());
		if (errorEmailSignUp) {
            model.addAttribute("errorEmail", errorEmailSignUp);
            errorEmailSignUp=false;
        }
		return "pagEstaticas/Sign Up";
	}
	/**
	 * Post del registro. Crea un nuevo usuario (no gestionado)
	 * @param a Nuevo usuario
	 * @return
	 */
	@PostMapping("/signup/submit")
	public String procesarRegistro(@ModelAttribute("usuario") Usuario a) {
		
		 if (s.comprobarEmail(a.getEmail())) {
	            errorEmailSignUp = true;
	            return "redirect:/signup";
	        } else {
	            errorEmailSignUp = false;
	            BCryptPasswordEncoder encriptar=new BCryptPasswordEncoder();
	            a.setPassword(encriptar.encode(a.getPassword()));
	            s.save(a);
	            return "redirect:/";
	        }
	}
}
