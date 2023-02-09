package com.project.people.persistence.jpa;

import com.project.people.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoJpaRepository extends JpaRepository<Endereco, Integer> {
}
