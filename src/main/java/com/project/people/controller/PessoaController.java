package com.project.people.controller;

import com.project.people.controller.request.PessoaRequest;
import com.project.people.model.Endereco;
import com.project.people.model.Pessoa;
import com.project.people.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    private Pessoa mapPessoaRequestToDomain(PessoaRequest pessoaRequest) {
        Pessoa pessoa = new Pessoa(
                pessoaRequest.getNome(),
                pessoaRequest.getDataNascimento());
        return pessoa;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa createPessoa (@RequestBody PessoaRequest pessoaRequest){
        return this.pessoaService.createPessoa(mapPessoaRequestToDomain(pessoaRequest));
    }

    @GetMapping("/{nomePessoa}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa findByNome(@PathVariable String nomePessoa){
        return this.pessoaService.findByNome(nomePessoa);
    }

    @PutMapping("/{nomePessoa}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa editPessoaByNome (@PathVariable String nomePessoa, @RequestBody PessoaRequest pessoaRequest){
        return this.pessoaService.updatePessoa(nomePessoa,mapPessoaRequestToDomain(pessoaRequest));
    }

    @PutMapping("/enderecoPrincipal/{nomePessoa}/{idEndereco}")
    @ResponseStatus(HttpStatus.OK)
    public Endereco setEnderecoPrincipalByPessoa (@PathVariable("idEndereco") int idEndereco,
                                                  @PathVariable("nomePessoa") String nomePessoa){
        return this.pessoaService.setEnderecoPrincipalByPessoa(nomePessoa,idEndereco);
    }

    @GetMapping("/listaPessoas")
    @ResponseStatus(HttpStatus.OK)
    public List<Pessoa> getAllPessoas (){
        return this.pessoaService.getAllPessoas();
    }

}
