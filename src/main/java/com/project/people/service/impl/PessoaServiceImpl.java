package com.project.people.service.impl;

import com.project.people.model.Pessoa;
import com.project.people.persistence.PessoaPersistenceAdapter;
import com.project.people.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaPersistenceAdapter pessoaPersistenceAdapter;

    @Override
    public Pessoa createPessoa(Pessoa pessoa) {
        return null;
    }

    @Override
    public Pessoa updatePessoa(Pessoa pessoa) {
        return null;
    }

    @Override
    public Pessoa getByNome(String nome) {
        return null;
    }

    @Override
    public List<Pessoa> getAllPessoas() {
        return null;
    }

}
