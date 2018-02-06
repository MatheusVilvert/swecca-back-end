package com.marari.swecca.controller;


import com.marari.swecca.model.Vendedor;
import com.marari.swecca.service.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;

    @PostMapping("vendedor/salvar")
    public Vendedor cadastrar(@RequestBody Vendedor vendedor){return vendedorService.salvar(vendedor);}

    @PutMapping("vendedor/alterar")
    public void editar(@RequestBody Vendedor vendedor){vendedorService.salvar(vendedor);}

    @GetMapping("vendedor/todos")
    public List<Vendedor> buscarTodos(){return vendedorService.buscarTodos();}

    @DeleteMapping("vendedor/{id}")
    public void excluir(@PathVariable("id")Integer id){
        Vendedor vendedor = vendedorService.buscarPorId(id);
        vendedorService.excluir(vendedor);

    }
}
