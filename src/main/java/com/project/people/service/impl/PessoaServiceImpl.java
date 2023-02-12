package com.project.people.service.impl;

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
        return this.pessoaPersistenceAdapter.save(pessoa);
    }

    @Override
    public Pessoa updatePessoa(String nomePessoa, Pessoa pessoa) {
        Pessoa pessoaToUpdate = pessoaPersistenceAdapter.findByName(nomePessoa);
        pessoaToUpdate.setNome(pessoa.getNome());
        pessoaToUpdate.setDataNascimento(pessoa.getDataNascimento());
        return this.pessoaPersistenceAdapter.save(pessoaToUpdate);
    }

    @Override
    public Endereco setEnderecoPrincipalByPessoa(String nomePessoa, int idEndereco) {
        Pessoa getPessoaToSetEnderecoPrincipal = this.pessoaPersistenceAdapter.findByName(nomePessoa);
        Endereco getEnderecoToSetPrincipal = this.enderecoPersistenceAdapter.findById(idEndereco).orElse(null);

        for (Endereco e : getPessoaToSetEnderecoPrincipal.getEnderecos()) {
            e.setEnderecoPrincipal(false);
        }

        getEnderecoToSetPrincipal.setEnderecoPrincipal(true);
        this.pessoaPersistenceAdapter.save(getPessoaToSetEnderecoPrincipal);
        return getEnderecoToSetPrincipal;
    }

    @Override
    public Pessoa findByNome(String nome) {
        return this.pessoaPersistenceAdapter.findByName(nome);
    }

    @Override
    public List<Pessoa> getAllPessoas() {
        return this.pessoaPersistenceAdapter.getAll();
    }


}
