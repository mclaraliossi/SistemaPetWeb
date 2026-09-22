package br.com.petweb.petweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AreaUsuarioController {

    @GetMapping("/area-usuario")
    public String areaUsuario() {
        return "usuario/areaUsuario";
    }
}

