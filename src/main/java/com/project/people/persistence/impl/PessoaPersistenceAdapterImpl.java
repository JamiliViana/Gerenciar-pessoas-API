package com.project.people.persistence.impl;

import com.project.people.persistence.PessoaPersistenceAdapter;
import com.project.people.persistence.jpa.PessoaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaPersistenceAdapterImpl implements PessoaPersistenceAdapter {

    private final PessoaJpaRepository pessoaJpaRepository;

    @Autowired
    public PessoaPersistenceAdapterImpl(PessoaJpaRepository pessoaJpaRepository) {
        this.pessoaJpaRepository = pessoaJpaRepository;
    }

}
