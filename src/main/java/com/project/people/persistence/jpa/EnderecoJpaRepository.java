package com.project.people.persistence.jpa;

import com.project.people.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoJpaRepository extends JpaRepository<Endereco, Integer> {
    Optional<List<Endereco>> findByPessoaNome(String nome);
}
