package com.example.demo.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FrontController {

    // Método para mostrar el formulario de inicio de sesión (GET)
    @RequestMapping(path = "/inicio", method = RequestMethod.GET)
    public String showLoginForm() {
        return "login";
    }

    // Método para procesar el formulario de inicio de sesión (POST)
    @RequestMapping(path = "/inicio", method = RequestMethod.POST)
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {
        // Verificar las credenciales (simulado solo para ejemplo, esto consultaría una base de datos)
        if (username.equals("admin") && password.equals("admin")) {
            // Login exitoso - Redirigir a la página de bienvenida
            model.addAttribute("username", username);
            return "welcome";
        } else {
            // Login fallido - Volver al formulario con mensaje de error
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

//    // Este método es el original, pero ahora usaremos el nuevo sistema de login
//    @RequestMapping(path = "/login", method = RequestMethod.POST)
//    @ResponseBody
//    public String login(@RequestParam String username, @RequestParam String password) {
//        if (username.equals("admin") && password.equals("admin")) {
//            return "LOGEADO";
//        }
//        return "INCORRECTO";
//    }
}