package br.com.petweb.petweb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.petweb.petweb.entity.Produto;
import br.com.petweb.petweb.service.ProdutoService;


@Controller
@RequestMapping("/catalogos")
public class CatalogoController {
    
    @Autowired
    private ProdutoService produtoService;

    //Método para listar todos os produtos
    @GetMapping("/listar")
    public String listar(Model model){
        List<Produto> produtos = produtoService.findAll();
        model.addAttribute("produtos", produtos);
        return "usuario/catalogoProduto";
    }

}
