package com.project.people.service.impl;

import com.project.people.config.advice.exception.EnderecoNotFoundException;
import com.project.people.config.advice.exception.IncompleteConstructorException;
import com.project.people.config.advice.exception.PessoaNotFoundException;
import com.project.people.model.Endereco;
import com.project.people.model.Pessoa;
import com.project.people.persistence.EnderecoPersistenceAdapter;
import com.project.people.persistence.PessoaPersistenceAdapter;
import com.project.people.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaPersistenceAdapter pessoaPersistenceAdapter;

    @Autowired
    private EnderecoPersistenceAdapter enderecoPersistenceAdapter;


    @Override
    public Pessoa createPessoa(Pessoa pessoa) {
        validParametersToCreatePessoa(pessoa);
        return this.pessoaPersistenceAdapter.save(pessoa);
    }

    private void validParametersToCreatePessoa(Pessoa pessoa){
        if (pessoa.getNome() == null || pessoa.getDataNascimento() == null){throw new IncompleteConstructorException();}
    }

    @Override
    public Pessoa updatePessoa(String nomePessoa, Pessoa pessoa) {
        Pessoa pessoaToUpdate = pessoaPersistenceAdapter.findByName(nomePessoa);
        if (pessoaToUpdate == null){throw new PessoaNotFoundException();}
        pessoaToUpdate.setNome(pessoa.getNome());
        pessoaToUpdate.setDataNascimento(pessoa.getDataNascimento());
        return this.pessoaPersistenceAdapter.save(pessoaToUpdate);
    }

    @Override
    public Endereco setEnderecoPrincipalByPessoa(String nomePessoa, int idEndereco) {
        Pessoa getPessoaToSetEnderecoPrincipal = this.pessoaPersistenceAdapter.findByName(nomePessoa);
        if (getPessoaToSetEnderecoPrincipal == null){throw new PessoaNotFoundException();}

        Endereco getEnderecoToSetPrincipal = this.enderecoPersistenceAdapter.findById(idEndereco).orElse(null);
        if (getEnderecoToSetPrincipal == null){throw new EnderecoNotFoundException();}

        for (Endereco e : getPessoaToSetEnderecoPrincipal.getEnderecos()) {
            e.setEnderecoPrincipal(false);
        }

        getEnderecoToSetPrincipal.setEnderecoPrincipal(true);
        this.pessoaPersistenceAdapter.save(getPessoaToSetEnderecoPrincipal);
        return getEnderecoToSetPrincipal;
    }

    @Override
    public Pessoa findByNome(String nome) {
        Pessoa getPessoa = this.pessoaPersistenceAdapter.findByName(nome);
        if (getPessoa == null){throw new PessoaNotFoundException();}
        return getPessoa;
    }

    @Override
    public List<Pessoa> getAllPessoas() {
        return this.pessoaPersistenceAdapter.getAll();
    }


}
