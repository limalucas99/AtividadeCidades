package br.com.danrley.br.com.danrley.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danrley.br.com.danrley.model.bean.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade,Long> {

}
