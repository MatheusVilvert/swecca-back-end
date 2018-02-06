package com.marari.swecca.service;


import com.marari.swecca.model.Cliente;
import com.marari.swecca.repository.ClienteRepository;
import com.marari.swecca.repository.EnderecoRepository;
import com.marari.swecca.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private VendedorRepository vendedorRepository;

    public Cliente salvar(Cliente cliente){
        enderecoRepository.save(cliente.getEndereco());
        cliente.setVendedor(vendedorRepository.findOne(cliente.getVendedor().getId()));
        return clienteRepository.save(cliente);
    }

    public List<Cliente> buscarTodos(){return clienteRepository.findAll();}

    public List<Cliente> buscarPorNome(String nome){return clienteRepository.buscaNome(nome);}

    public List<Cliente> buscarPorParametro(String nome, String nomeVendedor){
        return clienteRepository.buscaParametro(nome,nomeVendedor);
    }

    public List<Cliente> buscarPorParametro(String parametro){return clienteRepository.buscarPorParametro('%'+parametro+'%');}

    public List<Cliente> buscarPorNomeVendedor(String nomeVendedor){return clienteRepository.findByVendedorNome(nomeVendedor);}

    public List<Cliente> listaCliente(String nome,String nomeVendedor){
        if (nome != "" && nomeVendedor.equals("")){
            nome = '%'+nome+'%';
            return clienteRepository.buscaNome(nome);
        }else if (nome.equals("") && nomeVendedor != ""){
            return clienteRepository.findByVendedorNome(nomeVendedor);
        }else if (nome != "" && nomeVendedor != ""){
            return clienteRepository.findByNomeAndVendedorNome(nome,nomeVendedor);
        }else{
            return clienteRepository.findAll();
        }

//        else {
//            nome = null;
//            return clienteRepository.listaDeCliente(nome);
//        }
//        if (nomeVendedor != ""){
//            return clienteRepository.listaDeClientePorVendedor(nomeVendedor);
//        }


    }

    public void excluir(Cliente cliente){clienteRepository.delete(cliente);}

    public void editar(Cliente cliente){
        Cliente clienteExistente = clienteRepository.findOne(cliente.getId());
        if (clienteExistente != null){
            clienteRepository.save(cliente);
        }
    }

}
