package br.com.petweb.petweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petweb.petweb.entity.Cliente;
import br.com.petweb.petweb.repository.ClienteRepository;

@Service
public class ClienteService {

    //injeção de dependência do repositório de clientes
    @Autowired
    private ClienteRepository clienteRepository;

    //Método para salvar um cliente
    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    //Método para listar todos os clientes
    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }
}
