package com.marari.swecca.service;


import com.marari.swecca.model.Endereco;
import com.marari.swecca.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository enderecoRepository;

    public List<Endereco> buscarTodos(){return enderecoRepository.findAll();}

    public Endereco salvar(Endereco endereco){return enderecoRepository.save(endereco);}

    public void excluir(Endereco endereco){enderecoRepository.delete(endereco);}

    public void editar(Endereco endereco){
        Endereco enderecoExistente = enderecoRepository.findOne(endereco.getId());
        if (enderecoExistente != null){
            enderecoRepository.save(endereco);
        }
    }
}
