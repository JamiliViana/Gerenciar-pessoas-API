package com.project.people.service;

import com.project.people.model.Endereco;

import java.util.List;

public interface EnderecoService {
    Endereco createEndereco(Endereco endereco, String nomePessoa);
    List<Endereco> getAllEnderecosByNomePessoa(String nomePessoa);

}
