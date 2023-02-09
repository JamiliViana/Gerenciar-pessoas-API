package com.project.people.service;

import com.project.people.model.Endereco;

import java.util.List;

public interface EnderecoService {
    Endereco createEndereco(Endereco endereco);
    void enderecoPrincipal (Endereco endereco);
    List<Endereco> getAllEnderecoByPessoaNome(String nome);
}
