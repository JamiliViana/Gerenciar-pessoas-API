package com.project.people.persistence.impl;

import com.project.people.model.Endereco;
import com.project.people.persistence.EnderecoPersistenceAdapter;
import com.project.people.persistence.jpa.EnderecoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EnderecoPersistenceAdapterImpl implements EnderecoPersistenceAdapter {

    @Autowired
    private EnderecoJpaRepository enderecoJpaRepository;

    @Override
    public Endereco save(Endereco endereco) {
        return this.enderecoJpaRepository.save(endereco);
    }

    @Override
    public Optional<List<Endereco>> findByPessoaNome(String nome) {
        return this.enderecoJpaRepository.findByPessoaNome(nome);
    }

    @Override
    public Optional<Endereco> findById(int idEndereco) {
        return this.enderecoJpaRepository.findById(idEndereco);
    }
}
