package com.project.people.persistence;

import com.project.people.model.Endereco;

import java.util.List;
import java.util.Optional;

public interface EnderecoPersistenceAdapter {
    Endereco save(Endereco endereco);
    Optional<List<Endereco>> findByPessoaNome(String nome);
    Optional<Endereco> findById (int idEndereco);

}
