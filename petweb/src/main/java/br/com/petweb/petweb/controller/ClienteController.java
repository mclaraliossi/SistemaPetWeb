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

import br.com.petweb.petweb.entity.Cliente;
import br.com.petweb.petweb.service.ClienteService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    

    //Injeção de dependência da service de clientes 
    @Autowired
    private ClienteService clienteService;

    //Método para salvar cliente
    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Cliente cliente){
        clienteService.save(cliente);
        return "redirect:/clientes/listar";
    }

    //Método paa listar todos os clientes
    @GetMapping("/listar")
    public String listar(Model model){
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        return "cliente/listarCliente";
    }

    //Método para exibir o formulário de cadastro de cliente
    @GetMapping("/criar")
    public String criar(Model model){
        model.addAttribute("cliente", new Cliente());
        return "cliente/formularioCliente";
    }
    // Método para excluir cliente
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        clienteService.deleteById(id);
        return "redirect:/clientes/listar";
    }

    // Método para exibir o formulário de edição do cliente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteService.findById(id);
        model.addAttribute("cliente", cliente);
        return "cliente/formularioCliente";
    }

}
