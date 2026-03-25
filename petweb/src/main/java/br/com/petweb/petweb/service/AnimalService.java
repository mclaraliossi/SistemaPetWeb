package br.com.petweb.petweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petweb.petweb.entity.Animal;

import br.com.petweb.petweb.repository.AnimalRepository;

@Service
public class AnimalService {
    //injeção de dependência do repositório de clientes
    @Autowired
    private AnimalRepository animalRepository;

    //Método para salvar um cliente
    public Animal save(Animal animal){
        return animalRepository.save(animal);
    }

    //Método para listar todos os clientes
    public List<Animal> findAll(){
        return animalRepository.findAll();
    }
    // Método para excluir um cliente por ID
    public void deleteById(Integer id) {
        animalRepository.deleteById(id);
    }

    // Método para encontrar um cliente por ID
    public Animal findById(Integer id) {
        return animalRepository.findById(id).orElse(null);
    }
}
