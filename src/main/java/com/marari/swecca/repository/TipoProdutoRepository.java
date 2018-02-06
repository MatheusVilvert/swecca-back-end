package com.marari.swecca.repository;


import com.marari.swecca.model.TipoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TipoProdutoRepository extends JpaRepository<TipoProduto,Integer> {

    public List<TipoProduto> findByDescricao(String descricao);

}
