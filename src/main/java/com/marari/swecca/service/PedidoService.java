package com.marari.swecca.service;


import com.marari.swecca.model.Caixa;
import com.marari.swecca.model.Pedido;
import com.marari.swecca.model.Produto;
import com.marari.swecca.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CaixaRepository caixaRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    ItemPedidoRepository itemPedidoRepository;

    public Pedido salvar(Pedido pedido){
        Caixa caixa = new Caixa();
        double tot = 0;
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        pedido.setData(sdf.format(date));


        pedido.setCliente(clienteRepository.findOne(pedido.getCliente().getId()));
        pedido.setFormaPagamento(formaPagamentoRepository.findOne(pedido.getFormaPagamento().getId()));
        pedido.setUsuario(usuarioRepository.findOne(pedido.getUsuario().getId()));
        pedidoRepository.save(pedido);
        for (int i =0; i<pedido.getItensPedido().size(); i++){
            pedido.getItensPedido().get(i).setProduto(produtoRepository.findOne(pedido.getItensPedido().get(i).getProduto().getId()));
            Produto produto = new Produto();
            produto = produtoRepository.findOne(pedido.getItensPedido().get(i).getProduto().getId());
            produto.setQtdEstoque(produto.getQtdEstoque() - pedido.getItensPedido().get(i).getQuantidade());
            itemPedidoRepository.save(pedido.getItensPedido().get(i));
            tot+= pedido.getItensPedido().get(i).getProduto().getPrecoVenda() * pedido.getItensPedido().get(i).getQuantidade();
        }
        ////
        pedido.setValorTotal(tot);
        pedidoRepository.save(pedido);

        caixa.setData(pedido.getData());
        caixa.setDescricao(pedido.getInfo());
        caixa.setValor(pedido.getValorTotal());
        caixa.setCliente(pedido.getCliente());
        caixa.setFormaPagamento(pedido.getFormaPagamento());
        
        caixaRepository.save(caixa);


        return pedido;
    }

    public List<Pedido> buscarTodos(){return pedidoRepository.findAll();}

    public void excluir(Pedido pedido){
        Pedido pedidoDelete = pedidoRepository.findOne(pedido.getId());
        for (int i =0; i<pedidoDelete.getItensPedido().size(); i++){
            Produto produto = new Produto();
            produto = produtoRepository.findOne(pedidoDelete.getItensPedido().get(i).getProduto().getId());
            produto.setQtdEstoque(produto.getQtdEstoque() + pedidoDelete.getItensPedido().get(i).getQuantidade());
            produtoRepository.save(produto);

        }
        pedidoRepository.delete(pedido);
    }

    public double vendasHoje(){
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return pedidoRepository.vendasHoje(sdf.format(date));
    }

    public double vendasPeriodo(String dataIni, String dataFin){
        return pedidoRepository.vendasPeriodo(dataIni,dataFin);
    }

    public List<String> vendasDiaPeriodo(String dataIni, String dataFin){
        return pedidoRepository.vendasDiaPeriodo(dataIni,dataFin);
    }

    public List<Double> vendasValorPeriodo(String dataIni, String dataFin){
        return pedidoRepository.vendasValorPeriodo(dataIni,dataFin);
    }

    public List<Pedido> buscaParametro(String dataIni, String dataFin,String nomeCliente,String nomeVendedor){
        return pedidoRepository.buscaParametro(dataIni,dataFin,nomeCliente,nomeVendedor);
    }

    public List<Pedido> buscaPorCliente(String dataIni, String dataFin,String nomeCliente){
        return pedidoRepository.buscaPorCliente(dataIni,dataFin,nomeCliente);
    }

    public List<Pedido> buscaPorVendedor(String dataIni, String dataFin,String nomeVendedor){
        return pedidoRepository.buscaPorVendedor(dataIni,dataFin,nomeVendedor);
    }

    public List<Pedido> teste(){return pedidoRepository.teste();}

    public List<Pedido> listaPedido(String dataIni, String dataFin,String nomeCliente,String nomeVendedor){
        if (dataIni != "" && dataFin != "" && nomeCliente != "" && nomeVendedor != "" ){
            nomeCliente = '%'+nomeCliente+'%';
            nomeVendedor = '%'+nomeVendedor+'%';
           return pedidoRepository.buscaParametro(dataIni,dataFin,nomeVendedor,nomeCliente);
        }else if (dataIni != "" && dataFin != "" && nomeCliente.equals("") && nomeVendedor.equals("")){
            return pedidoRepository.buscaPorData(dataIni,dataFin);
        }else if (dataIni != "" && dataFin != "" && nomeCliente!= ""){
            nomeCliente = '%'+nomeCliente+'%';
            return pedidoRepository.buscaPorCliente(dataIni,dataFin,nomeCliente);
        }else if (dataIni != "" && dataFin != "" && nomeVendedor!= ""){
            nomeVendedor = '%'+nomeVendedor+'%';
            return pedidoRepository.buscaPorVendedor(dataIni,dataFin,nomeVendedor);
        }else {
            return null;
        }
    }

    public List<Pedido> buscaPorData(String dataIni, String dataFin){return pedidoRepository.buscaPorData(dataIni,dataFin);}

    public void editar(Pedido pedido){
        Pedido pedidoExistente = pedidoRepository.findOne(pedido.getId());
        if (pedidoExistente != null ){
            pedidoRepository.save(pedido);
        }
    }
}
