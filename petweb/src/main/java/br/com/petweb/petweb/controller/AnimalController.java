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
import br.com.petweb.petweb.service.AnimalService;


@Controller
@RequestMapping("/animais")
public class AnimalController {
    
        //Injeção de dependência da service de clientes 
        @Autowired
        private AnimalService animalService;
    
        //Método para salvar cliente
        @PostMapping("/salvar")
        public String salvar(@ModelAttribute Animal animal){
            animalService.save(animal);
            return "redirect:/animais/listar";
        }
    
        //Método paa listar todos os clientes
        @GetMapping("/listar")
        public String listar(Model model){
            List<Animal> animais = animalService.findAll();
            model.addAttribute("animais", animais);
            return "animal/listarAnimal";
        }
    
        //Método para exibir o formulário de cadastro de cliente
        @GetMapping("/criar")
        public String criar(Model model){
            model.addAttribute("animal", new Animal());
            return "animal/formularioAnimal";
        }
        // Método para excluir cliente
        @GetMapping("/excluir/{id}")
        public String excluir(@PathVariable Integer id) {
            animalService.deleteById(id);
            return "redirect:/animais/listar";
        }
    
        // Método para exibir o formulário de edição do cliente
        @GetMapping("/editar/{id}")
        public String editar(@PathVariable Integer id, Model model) {
            Animal animal = animalService.findById(id);
            model.addAttribute("animal", animal);
            return "animal/formularioAnimal";
        }
    
   
}
