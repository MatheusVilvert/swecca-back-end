package com.marari.swecca.service;


import com.marari.swecca.model.*;
import com.marari.swecca.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CaixaService {

    @Autowired
    private CaixaRepository caixaRepository;
    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private TipoDespesaRepository tipoDespesaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;


    public Caixa salvar(Caixa caixa){
        //setando forma de pagamento
        if (caixa.getFormaPagamento().getId() == null){
            formaPagamentoRepository.save(caixa.getFormaPagamento());
        }
        FormaPagamento formaPagamento = formaPagamentoRepository.findOne(caixa.getFormaPagamento().getId());
        caixa.setFormaPagamento(formaPagamento);
        //setando tipo da despesa
        if (caixa.getTipoDespesa().getId() == null){
            tipoDespesaRepository.save(caixa.getTipoDespesa());
        }
        TipoDespesa tipoDespesa = tipoDespesaRepository.findOne(caixa.getTipoDespesa().getId() );
        caixa.setTipoDespesa(tipoDespesa);

        //setando cliente
        if (caixa.getCliente().getId() == null){
            clienteRepository.save(caixa.getCliente());
        }
        Cliente cliente = clienteRepository.findOne(caixa.getCliente().getId() );
        caixa.setCliente(cliente);

        //setando fornecedor
        if (caixa.getFornecedor().getId() == null){
            fornecedorRepository.save(caixa.getFornecedor());
        }
        Fornecedor fornecedor = fornecedorRepository.findOne(caixa.getFornecedor().getId() );
        caixa.setFornecedor(fornecedor);
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        caixa.setData(sdf.format(date));
        return caixaRepository.save(caixa);

    }

    public List<Caixa> fluxoDeCaixa(String dataInicio, String dataFinal){return caixaRepository.fluxoDeCaixa(dataInicio,dataFinal);}

    public List<Caixa> buscarTodos(){return caixaRepository.findAll();}

    public List<Caixa> movimentoCaixa(String dataIni, String dataFin){
        return caixaRepository.movimentoCaixa(dataIni,dataFin);
    }

    public void excluir(Caixa caixa){caixaRepository.delete(caixa);}

    public void editar(Caixa caixa){
        Caixa caixaExistente = caixaRepository.findOne(caixa.getId());
        if (caixaExistente != null){
            caixaRepository.save(caixa);
        }
    }
}
