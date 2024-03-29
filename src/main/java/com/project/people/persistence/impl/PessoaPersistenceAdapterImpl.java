package com.project.people.persistence.impl;

import com.project.people.model.Pessoa;
import com.project.people.persistence.PessoaPersistenceAdapter;
import com.project.people.persistence.jpa.PessoaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PessoaPersistenceAdapterImpl implements PessoaPersistenceAdapter {

    @Autowired
    private PessoaJpaRepository pessoaJpaRepository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return this.pessoaJpaRepository.save(pessoa);
    }

    @Override
    public Pessoa findByName(String nome) {
        return this.pessoaJpaRepository.findByNome(nome);
    }
    @Override
    public List<Pessoa> getAll() {
        return this.pessoaJpaRepository.findAll();
    }

}
