package com.project.people.service;

import com.project.people.config.advice.exception.IncompleteConstructorException;
import com.project.people.config.advice.exception.PessoaNotFoundException;
import com.project.people.model.Endereco;
import com.project.people.model.Pessoa;
import com.project.people.persistence.EnderecoPersistenceAdapter;
import com.project.people.persistence.PessoaPersistenceAdapter;
import com.project.people.service.impl.EnderecoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {
    @Mock
    private PessoaPersistenceAdapter pessoaPersistenceAdapter;

    @Mock
    private EnderecoPersistenceAdapter enderecoPersistenceAdapter;

    @InjectMocks
    private EnderecoServiceImpl enderecoService;

    @Test
    public void deveCriarEndereco() {
        Endereco endereco = new Endereco("Rua Teste","123456-123",123,"Cidade Teste");
        Pessoa pessoa = new Pessoa("João", new Date());

        when(pessoaPersistenceAdapter.findByName("João")).thenReturn(pessoa);
        when(enderecoPersistenceAdapter.save(endereco)).thenReturn(endereco);

        Endereco result = enderecoService.createEndereco(endereco,"João");

        assertEquals(pessoa, result.getPessoa());
        assertEquals(endereco.getLogradouro(), result.getLogradouro());
        assertEquals(endereco.getCep(), result.getCep());
        assertEquals(endereco.getNumero(), result.getNumero());
        assertEquals(endereco.getCidade(), result.getCidade());
    }

    @Test
    public void deveRetornarErroAoCriarEnderecoSemLogradouro() {
        Endereco endereco = new Endereco(null, "12345-678", 1, "São Paulo");

        assertThrows(IncompleteConstructorException.class, () -> {
            enderecoService.createEndereco(endereco,"Fulano");
        });
    }

    @Test
    public void deveRetornarErroAoCriarEnderecoSemCep() {
        Endereco endereco = new Endereco("Rua A", null, 1, "São Paulo");

        assertThrows(IncompleteConstructorException.class, () -> {
            enderecoService.createEndereco(endereco,"Fulano");
        });
    }

    @Test
    public void deveRetornarAllEnderecosByPessoa() {
        List<Endereco> enderecos = Arrays.asList(
                new Endereco("Rua Teste1","123456-123",123,"Cidade Teste"),
                new Endereco("Rua Teste2","123456",12,"Cidade Teste"));

        Pessoa pessoa = new Pessoa("João", new Date());

        when(pessoaPersistenceAdapter.findByName(pessoa.getNome())).thenReturn(pessoa);
        pessoa.setEnderecos(enderecos);

        List<Endereco> result = enderecoService.getAllEnderecosByNomePessoa(pessoa.getNome());

        assertEquals(2, result.size());
    }

    @Test
    public void deveRetornarErroAoChamarGetAllEndercoByNomePessoaSemExistirPessoa() {
        List<Endereco> enderecos = Arrays.asList(
                new Endereco("Rua Teste1","123456-123",123,"Cidade Teste"),
                new Endereco("Rua Teste2","123456",12,"Cidade Teste"));


        assertThrows(PessoaNotFoundException.class, () -> {
            enderecoService.getAllEnderecosByNomePessoa("João");
        });

    }


}
