package com.project.people.service;

import com.project.people.model.Pessoa;

import java.util.List;

public interface PessoaService {
    Pessoa createPessoa(Pessoa pessoa);
    Pessoa updatePessoa(Pessoa pessoa);
    Pessoa getByNome(String nome);
    List<Pessoa> getAllPessoas();

}
