package br.com.petweb.petweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petweb.petweb.entity.Animal;
import br.com.petweb.petweb.entity.Consulta;
import br.com.petweb.petweb.entity.Veterinario;
import br.com.petweb.petweb.service.AnimalService;
import br.com.petweb.petweb.service.ConsultaService;
import br.com.petweb.petweb.service.VeterinarioService;



@Controller
@RequestMapping("/consultas")
public class ConsultaController {
    
     //Injeção de dependência da service de clientes 
    @Autowired
    private ConsultaService consultaService;
    @Autowired
    private VeterinarioService veterinarioService;
    @Autowired
    private AnimalService animalService;

    //Método para salvar cliente
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Consulta consulta){
        consultaService.save(consulta);
        return "redirect:/consultas/listar";
    }

    //Método para listar todos os clientes
    @GetMapping("/listar")
    public String listar(Model model){
        List<Consulta> consultas = consultaService.findAll();
        model.addAttribute("consultas", consultas);
        return "consulta/listarConsulta";
    }

    //Método para exibir o formulário de cadastro de cliente
    @GetMapping("/criar")
    public String criar(Model model){
        model.addAttribute("consulta", new Consulta());
        List<Veterinario> veterinarios = veterinarioService.findAll();
        model.addAttribute("veterinarios", veterinarios);
        List<Animal> animais = animalService.findAll();
        model.addAttribute("animais", animais);
        return "consulta/formularioConsulta";
    }
    // Método para excluir cliente
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        consultaService.deleteById(id);
        return "redirect:/consultas/listar";
    }

    // Método para exibir o formulário de edição do cliente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Consulta consulta = consultaService.findById(id);
        model.addAttribute("consulta", consulta);
        return "consulta/formularioConsulta";
    }
}
