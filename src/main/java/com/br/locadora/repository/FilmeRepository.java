package com.br.locadora.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.locadora.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {

}
