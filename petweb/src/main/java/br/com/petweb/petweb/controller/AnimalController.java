package br.com.petweb.petweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.petweb.petweb.entity.Animal;
import br.com.petweb.petweb.entity.Cliente;
import br.com.petweb.petweb.service.AnimalService;
import br.com.petweb.petweb.service.ClienteService;


@Controller
@RequestMapping("/animais")
public class AnimalController {
    
        //Injeção de dependência da service de clientes 
        @Autowired
        private AnimalService animalService;

        @Autowired
        private ClienteService clienteService;
    
        //Método para salvar cliente
        @PostMapping("/salvar")
        public String salvar(@ModelAttribute Animal animal,
             @RequestParam("foto") MultipartFile foto){
                try{
                    if(!foto.isEmpty()){
                        animal.setFotoAnimal(foto.getBytes());
                        animal.setTipoFotoAnimal(foto.getContentType());
                    }else if(animal.getIdAnimal() != null){
                        Animal animalExistente = animalService.findById(animal.getIdAnimal());
                        if(animalExistente != null){
                            animal.setFotoAnimal(animalExistente.getFotoAnimal());
                            animal.setTipoFotoAnimal(animalExistente.getTipoFotoAnimal());
                        }
                    }
                    animalService.save(animal);
                }catch(Exception ex){
                    ex.printStackTrace();
                }
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
            List<Cliente> clientes = clienteService.findAll();
            model.addAttribute("clientes", clientes);
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
            List<Cliente> clientes = clienteService.findAll();
            model.addAttribute("clientes", clientes);
            return "animal/formularioAnimal";
        }

        @GetMapping("/foto/{id}")
        public ResponseEntity<byte[]> foto(@PathVariable Integer id){
            Animal animal = animalService.findById(id);
            if(animal == null && animal.getFotoAnimal() == null){
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(animal.getTipoFotoAnimal()))
                    .body(animal.getFotoAnimal());
        }
    
   
}
