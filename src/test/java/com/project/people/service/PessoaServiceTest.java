package com.project.people.service;


import com.project.people.config.advice.exception.EnderecoNotFoundException;
import com.project.people.config.advice.exception.IncompleteConstructorException;
import com.project.people.config.advice.exception.PessoaNotFoundException;
import com.project.people.model.Endereco;
import com.project.people.model.Pessoa;
import com.project.people.persistence.EnderecoPersistenceAdapter;
import com.project.people.persistence.PessoaPersistenceAdapter;
import com.project.people.service.impl.PessoaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {
    @Mock
    private PessoaPersistenceAdapter pessoaPersistenceAdapter;

    @Mock
    private EnderecoPersistenceAdapter enderecoPersistenceAdapter;

    @InjectMocks
    private PessoaServiceImpl pessoaService;

    @Test
    public void deveCriarPessoa() {
        Pessoa pessoa = new Pessoa("João", new Date());

        when(pessoaPersistenceAdapter.save(pessoa)).thenReturn(pessoa);

        Pessoa result = pessoaService.createPessoa(pessoa);

        assertEquals(pessoa, result);
    }

    @Test
    public void deveRetornarErroAoCriarPessoaSemNome() {
        Pessoa pessoa = new Pessoa(null, new Date());

        assertThrows(IncompleteConstructorException.class, () -> {
            pessoaService.createPessoa(pessoa);
        });
    }

    @Test
    public void deveRetornarErroAoCriarPessoaSemDataNascimento() {
        Pessoa pessoa = new Pessoa("João", null);

        assertThrows(IncompleteConstructorException.class, () -> {
            pessoaService.createPessoa(pessoa);
        });
    }

    @Test
    public void deveAtualizarPessoaAoChamarUpdate() {
        String nomePessoa = "João";
        Pessoa pessoa = new Pessoa("José", new Date());
        Pessoa pessoaToUpdate = new Pessoa(nomePessoa, new Date());

        when(pessoaPersistenceAdapter.findByName(nomePessoa)).thenReturn(pessoaToUpdate);
        when(pessoaPersistenceAdapter.save(pessoaToUpdate)).thenReturn(pessoaToUpdate);

        Pessoa result = pessoaService.updatePessoa(nomePessoa, pessoa);

        assertEquals(pessoa.getNome(), result.getNome());
        assertEquals(pessoa.getDataNascimento(), result.getDataNascimento());
    }

    @Test
    public void deveRetornarErroAoChamarUpdatePessoaComPessoaNaoCriada() {
        String nomePessoa = "João";
        Pessoa pessoa = new Pessoa(null, new Date());

        when(pessoaPersistenceAdapter.findByName(nomePessoa)).thenReturn(null);

        assertThrows(PessoaNotFoundException.class, () -> {
            pessoaService.updatePessoa(nomePessoa, pessoa);
        });
    }

    @Test
    public void deveSetarEnderecoPrincipalParaPessoa() {
        Pessoa pessoa = new Pessoa("João", new Date());
        Endereco endereco1 = new Endereco("Rua A", "12345-678", 10, "São Paulo");
        endereco1.setEnderecoPrincipal(true);
        Endereco endereco2 = new Endereco("Rua B", "54321-876", 20, "Rio de Janeiro");

        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(endereco1);
        enderecos.add(endereco2);
        pessoa.setEnderecos(enderecos);

        when(pessoaPersistenceAdapter.findByName("João")).thenReturn(pessoa);
        when(enderecoPersistenceAdapter.findById(2)).thenReturn(Optional.of(endereco2));

        Endereco result = pessoaService.setEnderecoPrincipalByPessoa("João", 2);

        assertFalse(endereco1.getEnderecoPrincipal());
        assertTrue(endereco2.getEnderecoPrincipal());
    }

    @Test
    public void deveRetornarErroAoChamarSetEnderecoPrincipalByPessoaComPessoaNaoCriada() {
        when(pessoaPersistenceAdapter.findByName("Maria")).thenReturn(null);

        assertThrows(PessoaNotFoundException.class, () -> {
            pessoaService.setEnderecoPrincipalByPessoa("Maria", 1);
        });

    }

    @Test
    public void deveRetornarErroAoChamarSetEnderecoPrincipalByPessoaComEnderecoNaoCriado() {
        Pessoa pessoa = new Pessoa("João", new Date());

        when(pessoaPersistenceAdapter.findByName("João")).thenReturn(pessoa);
        when(enderecoPersistenceAdapter.findById(3)).thenReturn(Optional.empty());

        assertThrows(EnderecoNotFoundException.class, () -> {
            pessoaService.setEnderecoPrincipalByPessoa("João", 3);
        });

    }

    @Test
    public void deveRetornarPessoaAoChamarByNome() {
        Pessoa pessoa = new Pessoa("João", new Date());

        when(pessoaPersistenceAdapter.save(pessoa)).thenReturn(pessoa);
        when(pessoaPersistenceAdapter.findByName("João")).thenReturn(pessoa);

        pessoaPersistenceAdapter.save(pessoa);
        Pessoa result = pessoaService.findByNome("João");

        assertEquals(pessoa.getNome(), result.getNome());
        assertEquals(pessoa.getDataNascimento(), result.getDataNascimento());
    }

    @Test
    public void deveRetornarErroAoChamarByNomeComPessoaNãoCriada() {
        when(pessoaPersistenceAdapter.findByName(any())).thenReturn(null);

        assertThrows(PessoaNotFoundException.class, () -> {
            pessoaService.findByNome("Fulano");
        });
    }

    @Test
    public void deveRetornarListaPessoa() {
        List<Pessoa> pessoas = Arrays.asList(
                new Pessoa("Fulano", new Date(2000, 1, 1)),
                new Pessoa("Ciclano", new Date(2001, 2, 2)));

        when(pessoaPersistenceAdapter.getAll()).thenReturn(pessoas);

        List<Pessoa> resultListPessoa = pessoaService.getAllPessoas();

        assertEquals(2, resultListPessoa.size());
    }
}
