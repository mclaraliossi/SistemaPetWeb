package br.com.petweb.petweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petweb.petweb.entity.Veterinario;
import br.com.petweb.petweb.repository.VeterinarioRepository;



@Service
public class VeterinarioService {
    
    @Autowired
    private  VeterinarioRepository veterinarioRepository;

    //Método para salvar um cliente
    public Veterinario save(Veterinario veterinario){
        return veterinarioRepository.save(veterinario);
    }

    //Método para listar todos os clientes
    public List<Veterinario> findAll(){
        return veterinarioRepository.findAll();
    }
    // Método para excluir um cliente por ID
    public void deleteById(Integer id) {
        veterinarioRepository.deleteById(id);
    }

    // Método para encontrar um cliente por ID
    public Veterinario findById(Integer id) {
        return veterinarioRepository.findById(id).orElse(null);
    }
}
