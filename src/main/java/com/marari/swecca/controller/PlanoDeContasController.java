package com.marari.swecca.controller;

import com.marari.swecca.model.PlanoDeContas;
import com.marari.swecca.service.PlanoDeContasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PlanoDeContasController {

    @Autowired
   private PlanoDeContasService planoDeContasService;

    @PostMapping("plano/todos")
    public List<PlanoDeContas> todos(){return planoDeContasService.PlanoDeContas();}
}
