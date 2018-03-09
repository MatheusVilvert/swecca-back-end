package com.marari.swecca.service;

import com.marari.swecca.model.PlanoDeContas;
import com.marari.swecca.repository.PlanoDeContasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanoDeContasService {

    @Autowired
    private PlanoDeContasRepository planoDeContasRepository;

    public List<PlanoDeContas> PlanoDeContas(){return planoDeContasRepository.findAll();}

    public void salvar(PlanoDeContas planoDeContas){planoDeContasRepository.save(planoDeContas);}

}
