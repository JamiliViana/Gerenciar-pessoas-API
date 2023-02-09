package com.project.people.persistence;

import com.project.people.model.Pessoa;

import java.util.List;

public interface PessoaPersistenceAdapter {
    Pessoa save(Pessoa pessoa);
    Pessoa findByName(String nome);
    List<Pessoa> getAll();

}
