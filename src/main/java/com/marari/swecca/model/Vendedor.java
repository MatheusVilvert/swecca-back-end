package com.marari.swecca.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Vendedor extends Usuario{
    @Id
    @GeneratedValue
    private Integer id;
    private Double meta;
    private String regiao;
    private String telefone;

    public Vendedor(Integer id, String nome, String email, String senha, Perfil perfil, Double meta, String regiao,String telefone) {
        super(id, nome, email, senha, perfil);
        this.meta = meta;
        this.regiao = regiao;
        this.telefone = telefone;
    }

    public Vendedor(){

    }
}
