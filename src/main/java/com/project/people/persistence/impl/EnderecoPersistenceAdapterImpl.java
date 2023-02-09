package com.project.people.persistence.impl;

import com.project.people.model.Endereco;
import com.project.people.persistence.EnderecoPersistenceAdapter;
import com.project.people.persistence.jpa.EnderecoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EnderecoPersistenceAdapterImpl implements EnderecoPersistenceAdapter {

    @Autowired
    private EnderecoJpaRepository enderecoJpaRepository;

    @Override
    public Endereco save(Endereco endereco) {
        return null;
    }

    @Override
    public List<Endereco> findByPessoaNome(String nome) {
        return null;
    }
}
