package com.project.people.service;

import com.project.people.model.Endereco;
import com.project.people.model.Pessoa;

import java.util.List;

public interface PessoaService {
    Pessoa createPessoa(Pessoa pessoa);
    Pessoa updatePessoa(String nomePessoa,Pessoa pessoa);
    Endereco setEnderecoPrincipalByPessoa(String nomePessoa, int idEndereco);
    Pessoa findByNome(String nomePessoa);
    List<Pessoa> getAllPessoas();


}
