package com.project.people.service.impl;

import com.project.people.config.advice.exception.PessoaNotFoundException;
import com.project.people.model.Endereco;
import com.project.people.model.Pessoa;
import com.project.people.persistence.EnderecoPersistenceAdapter;
import com.project.people.persistence.PessoaPersistenceAdapter;
import com.project.people.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    @Autowired
    private EnderecoPersistenceAdapter enderecoPersistenceAdapter;

    @Autowired
    private PessoaPersistenceAdapter pessoaPersistenceAdapter;

    @Override
    public Endereco createEndereco(Endereco endereco, String nomePessoa) {
        Pessoa pessoa = this.pessoaPersistenceAdapter.findByName(nomePessoa);
        if(pessoa == null){throw new PessoaNotFoundException();}
        endereco.setPessoa(pessoa);
        return this.enderecoPersistenceAdapter.save(endereco);
    }


    @Override
    public List<Endereco> getAllEnderecosByNomePessoa(String nomePessoa) {
        Pessoa pessoaToGetEnderecos = this.pessoaPersistenceAdapter.findByName(nomePessoa);
        if (pessoaToGetEnderecos == null){throw new PessoaNotFoundException();}
        return pessoaToGetEnderecos.getEnderecos();
    }
}
