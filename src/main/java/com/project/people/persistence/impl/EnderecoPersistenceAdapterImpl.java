package com.project.people.persistence.impl;

import com.project.people.persistence.EnderecoPersistenceAdapter;
import com.project.people.persistence.jpa.EnderecoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoPersistenceAdapterImpl implements EnderecoPersistenceAdapter {

    private final EnderecoJpaRepository enderecoJpaRepository;

    @Autowired
    public EnderecoPersistenceAdapterImpl(EnderecoJpaRepository enderecoJpaRepository) {
        this.enderecoJpaRepository = enderecoJpaRepository;
    }

}
