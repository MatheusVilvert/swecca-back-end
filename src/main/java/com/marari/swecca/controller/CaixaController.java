package com.marari.swecca.controller;


import com.marari.swecca.model.Caixa;
import com.marari.swecca.service.CaixaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class CaixaController {

    @Autowired
    private CaixaService caixaService;

    @PostMapping("caixa/salvar")
    public Caixa cadastrar(@RequestBody Caixa caixa){
        return caixaService.salvar(caixa);
    }

    @PutMapping("caixa/alterar")
    public void editar(@RequestBody Caixa caixa){
        caixaService.editar(caixa);
    }

    @GetMapping("caixa/todos")
    public List<Caixa> buscarTodos(){return caixaService.buscarTodos();}

    @GetMapping("caixa/fluxo")
    public List<Caixa> fluxoDeCaixa(@Param("dataInicio")String dataInicio, @Param("dataFinal")String dataFinal){return caixaService.fluxoDeCaixa(dataInicio,dataFinal);}

    @GetMapping("caixa/lista")
    public List<Caixa> movimentoCaixa(@Param("dataIni")String dataIni, @Param("dataFin")String dataFin){
        return caixaService.movimentoCaixa(dataIni,dataFin);
    }

    @DeleteMapping("caixa/{id}")
    public void excluir(@PathVariable("id")Integer id){
        Caixa caixa = new Caixa();
        caixa.setId(id);
        caixaService.excluir(caixa);
    }
}
