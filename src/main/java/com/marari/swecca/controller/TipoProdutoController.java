package com.marari.swecca.controller;

import com.marari.swecca.model.TipoProduto;
import com.marari.swecca.service.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class TipoProdutoController {
    @Autowired
    private TipoProdutoService tipoProdutoService;

    @PostMapping("tipoproduto/salvar")
    public TipoProduto cadastrar(@RequestBody TipoProduto tipoProduto){
        return tipoProdutoService.salvar(tipoProduto);
    }

    @GetMapping("tipoproduto/todos")
    public List<TipoProduto> buscarTodos(){
        return tipoProdutoService.buscarTodos();
    }

    @GetMapping("tipoproduto/descricao")
    public List<TipoProduto> buscarPorDescricao(@RequestParam("descicao")String descricao){
        return tipoProdutoService.buscarDescricao(descricao);
    }

    @DeleteMapping("tipoproduto/{id}")
    public void excluir(@PathVariable("id")Integer id){
        TipoProduto tipoProduto = new TipoProduto();
        tipoProduto.setId(id);
        tipoProdutoService.excluir(tipoProduto);
    }

    @PutMapping("tipoproduto/alterar")
    public void editar(@RequestBody TipoProduto tipoProduto){
        tipoProdutoService.editar(tipoProduto);
    }
}
