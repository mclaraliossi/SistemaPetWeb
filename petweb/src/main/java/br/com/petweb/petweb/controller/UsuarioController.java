package br.com.petweb.petweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.petweb.petweb.service.UsuarioService;
import br.com.petweb.petweb.entity.Usuario;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);
        return "/login";
    };

    @GetMapping("/criar")
    public String criarForm(Model model){
        model.addAttribute("usuarios", new Usuario());
        return "/usuario/usuarioFormulario";
    }
    // Recuperação de senha
    @GetMapping("/recuperar-senha")
    public String recuperarSenhaForm() {
        return "/usuario/recuperarSenha";
    }

    @PostMapping("/solicitar-recuperacao")
    public String solicitarRecuperacao(@RequestParam String email, Model model) {
        try {
            usuarioService.solicitarRecuperacaoSenha(email);
            model.addAttribute("mensagem", "Email de recuperação enviado com sucesso!");
            return "/usuario/recuperarSenha";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "/usuario/recuperarSenha";
        }
    }

    @GetMapping("/redefinir-senha")
    public String redefinirSenhaForm(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "/usuario/redefinirSenha";
    }

    @PostMapping("/redefinir-senha")
    public String redefinirSenha(@RequestParam String token, @RequestParam String novaSenha, Model model) {
        try {
            usuarioService.redefinirSenha(token, novaSenha);
            model.addAttribute("mensagem", "Senha redefinida com sucesso!");
            return "redirect:/login";
        } catch (Exception e) {
            model.addAttribute("erro", e.getMessage());
            return "/usuario/redefinirSenha?token=" + token;
        }
    }



 
}
