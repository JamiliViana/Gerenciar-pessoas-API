package com.project.people.persistence.jpa;

import com.project.people.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJpaRepository extends JpaRepository<Pessoa, Integer> {

}
