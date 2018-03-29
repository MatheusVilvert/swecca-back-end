package com.marari.swecca.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Estoque {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Produto produto;
    private String dataLanc;
    private String entrada = "ENTRADA";
    private String saida = "SAÍDA";
    private String transf = "TRANSFERÊNCIA";
    private String historico;
    private Double estAtual;
    private Double qtdEntrada;
    private Double qtdSaida;
    private Double valUnit;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Fornecedor fornecedor;
    private Integer nfOuPedido;


}

