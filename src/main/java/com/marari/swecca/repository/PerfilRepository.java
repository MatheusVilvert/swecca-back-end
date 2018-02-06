package com.marari.swecca.repository;

import com.marari.swecca.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil,Integer> {

    Perfil findByDescricao(String descricao);

}
