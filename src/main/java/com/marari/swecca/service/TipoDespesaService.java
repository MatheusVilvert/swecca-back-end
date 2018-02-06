package com.marari.swecca.service;

import com.marari.swecca.model.TipoDespesa;
import com.marari.swecca.repository.TipoDespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoDespesaService {

    @Autowired
    TipoDespesaRepository tipoDespesaRepository;

    public TipoDespesa salvar(TipoDespesa tipoDespesa){
        return tipoDespesaRepository.save(tipoDespesa);
    }

    public List<TipoDespesa> buscarTodos(){
        return tipoDespesaRepository.findAll();
    }

    public List<TipoDespesa> buscarPorDescricao(String descricao){
        return tipoDespesaRepository.findByDescricao(descricao);
    }

    public void excluir(TipoDespesa tipoDespesa){
        tipoDespesaRepository.delete(tipoDespesa);
    }

    public void editar(TipoDespesa tipoDespesa){
        TipoDespesa tipoDespesaExistente = tipoDespesaRepository.findOne(tipoDespesa.getId());
        if (tipoDespesaExistente != null){
            tipoDespesaRepository.save(tipoDespesa);
        }
    }
}
