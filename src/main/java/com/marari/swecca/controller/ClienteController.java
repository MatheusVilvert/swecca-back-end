package com.marari.swecca.controller;


import com.marari.swecca.model.Cliente;
import com.marari.swecca.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("cliente/salvar")
    public Cliente cadastrar(@RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @PutMapping("cliente/alterar")
    public void editar(@RequestBody Cliente cliente){
        clienteService.editar(cliente);
    }

    @GetMapping("cliente/todos")
    public List<Cliente> buscarTodos(){return clienteService.buscarTodos();}

    @GetMapping("cliente/parametro")
    public List<Cliente> buscarPorParametro(@Param("parametro") String parametro){return clienteService.buscarPorParametro(parametro);}

    @GetMapping("cliente/lista")
    public List<Cliente> listaCliente(@Param("nome")String nome, @Param("nomeVendedor")String nomeVendedor){
        return clienteService.listaCliente(nome,nomeVendedor);
    }

    @GetMapping("cliente/nome")
    public List<Cliente> buscarPorNome(@Param("nome")String nome){return clienteService.buscarPorNome(nome);}

    @DeleteMapping("cliente/{id}")
    public void excluir(@PathVariable("id")Integer id){
        Cliente cliente = new Cliente();
        cliente.setId(id);
        clienteService.excluir(cliente);
    }
}
