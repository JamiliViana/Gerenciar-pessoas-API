package com.project.people.service.impl;

import com.project.people.model.Endereco;
import com.project.people.persistence.EnderecoPersistenceAdapter;
import com.project.people.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoPersistenceAdapter enderecoPersistenceAdapter;

    @Override
    public Endereco createEndereco(Endereco endereco) {
        return null;
    }

    @Override
    public void enderecoPrincipal(Endereco endereco) {

    }

    @Override
    public List<Endereco> getAllEnderecoByPessoaNome(String nome) {
        return null;
    }
}
