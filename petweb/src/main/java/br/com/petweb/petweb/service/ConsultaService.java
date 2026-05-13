package br.com.petweb.petweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petweb.petweb.entity.Consulta;
import br.com.petweb.petweb.repository.ConsultaRepository;



@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    //Método para salvar um cliente
    public Consulta save(Consulta consulta){
        return consultaRepository.save(consulta);
    }

    //Método para listar todos os clientes
    public List<Consulta> findAll(){
        return consultaRepository.findAll();
    }
    // Método para excluir um cliente por ID
    public void deleteById(Integer id) {
        consultaRepository.deleteById(id);
    }

    // Método para encontrar um cliente por ID
    public Consulta findById(Integer id) {
        return consultaRepository.findById(id).orElse(null);
    }
}
