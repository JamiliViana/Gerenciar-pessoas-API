package com.project.people.controller;

import com.project.people.model.Endereco;
import com.project.people.model.Pessoa;
import com.project.people.service.PessoaService;
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa createPessoa (@RequestBody Pessoa pessoa){
        return this.pessoaService.createPessoa(pessoa);
    }

    @GetMapping("/{nomePessoa}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa findByNome(@PathVariable String nomePessoa){
        return this.pessoaService.findByNome(nomePessoa);
    }

    @PutMapping("/{nomePessoa}")
    @ResponseStatus(HttpStatus.OK)
    public Pessoa editPessoaByNome (@PathVariable String nomePessoa, @RequestBody Pessoa pessoa){
        return this.pessoaService.updatePessoa(nomePessoa,pessoa);
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
