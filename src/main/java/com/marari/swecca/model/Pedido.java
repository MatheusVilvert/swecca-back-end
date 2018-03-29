package com.marari.swecca.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue
    private Integer id;
    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn
    private List<ItemPedido> itensPedido;
    private Double valorTotal;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Usuario usuario;
    private String info;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Cliente cliente;
    private String data;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private FormaPagamento formaPagamento;

    public Pedido(List<ItemPedido> itensPedido, Double valorTotal, Usuario usuario, String info, Cliente cliente, String data, FormaPagamento formaPagamento) {
        this.itensPedido = itensPedido;
        this.valorTotal = valorTotal;
        this.usuario = usuario;
        this.info = info;
        this.cliente = cliente;
        this.data = data;
        this.formaPagamento = formaPagamento;
    }

    public Pedido() {
    }
}
