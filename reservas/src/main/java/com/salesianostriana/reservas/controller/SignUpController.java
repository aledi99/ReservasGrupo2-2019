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
@Controller
public class SignUpController {
	@Autowired
	UsuarioServicio s;
	@GetMapping("/signup")
    public String getRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "pagEstaticas/Sign Up";
    }
    
    @PostMapping("/signup/submit")
    public String procesarRegistro(@ModelAttribute("usuario") Usuario a) {
        BCryptPasswordEncoder p = new BCryptPasswordEncoder();
        a.setPassword(p.encode(a.getPassword()));
        s.save(a);
        return "redirect:/";
    }
}
