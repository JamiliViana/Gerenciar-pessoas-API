package com.project.people.config.advice;


import com.project.people.config.advice.exception.EnderecoNotFoundException;
import com.project.people.config.advice.exception.IncompleteConstructorException;
import com.project.people.config.advice.exception.PessoaNotFoundException;
import com.project.people.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ErrorHandler{

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PessoaNotFoundException.class)
    public ResponseEntity<ErrorMessage> PessoaNotFoundExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage("Pessoa não localizada"), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EnderecoNotFoundException.class)
    public ResponseEntity<ErrorMessage> EnderecoNotFoundExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage("Endereço não localizado"), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IncompleteConstructorException.class)
    public ResponseEntity<ErrorMessage> IncompleteConstructorExceptionHandler(Exception exception) {
        return new ResponseEntity<>(new ErrorMessage("Necessário preencher todos os campos"), HttpStatus.BAD_REQUEST);
    }

}
