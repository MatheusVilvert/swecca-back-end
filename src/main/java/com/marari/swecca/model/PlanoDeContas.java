package com.marari.swecca.model;

import com.marari.swecca.repository.PlanoDeContasRepository;
import com.marari.swecca.service.PlanoDeContasService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
public class PlanoDeContas {
    @Id
    @GeneratedValue
    private Integer id;
    private String descricao;

    public PlanoDeContas() {}
}
