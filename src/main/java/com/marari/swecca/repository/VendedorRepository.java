package com.marari.swecca.repository;


import com.marari.swecca.model.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor,Integer> {

    public Vendedor findById(Integer id);
}
