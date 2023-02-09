package com.project.people.persistence;

import com.project.people.model.Endereco;

import java.util.List;

public interface EnderecoPersistenceAdapter {
    Endereco save(Endereco endereco);
    List<Endereco> findByPessoaNome(String nome);

}
