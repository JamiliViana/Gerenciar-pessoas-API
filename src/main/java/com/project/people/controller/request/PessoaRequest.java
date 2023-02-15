package com.project.people.controller.request;

import java.util.Date;

public class PessoaRequest {
    private String nome;
    private Date dataNascimento;

    public PessoaRequest() {
    }

    public PessoaRequest(String nome, Date dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
