package com.project.people.controller;

import com.project.people.controller.request.EnderecoRequest;
import com.project.people.model.Endereco;
import com.project.people.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/endereco")
public class EnderecoController {

    private Endereco mapEnderecoRequestToDomain(EnderecoRequest enderecoRequest) {
        Endereco endereco = new Endereco(
                enderecoRequest.getLogradouro(),
                enderecoRequest.getCep(),
                enderecoRequest.getNumero(),
                enderecoRequest.getCidade());
        return endereco;
    }

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping("/{nomePessoa}")
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco createEndereco(@PathVariable String nomePessoa, @RequestBody EnderecoRequest enderecoRequest) {
        return this.enderecoService.createEndereco(mapEnderecoRequestToDomain(enderecoRequest), nomePessoa);
    }


    @GetMapping("/{nomePessoa}")
    @ResponseStatus(HttpStatus.OK)
    public List<Endereco> getAllEnderecosByNomePessoa(@PathVariable String nomePessoa) {
        return this.enderecoService.getAllEnderecosByNomePessoa(nomePessoa);
    }
}
