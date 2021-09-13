package com.br.locadora.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.br.locadora.exception.FilmeNotFoundException;
import com.br.locadora.exception.NaoAfetadoException;
import com.br.locadora.model.DetalheErro;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(FilmeNotFoundException.class)
    public ResponseEntity<DetalheErro> handlerFilmeNotFoundException(FilmeNotFoundException e, HttpServletRequest request) {
        DetalheErro detalheErro = new DetalheErro();
        detalheErro.setStatus(404l);
        detalheErro.setTitulo("Filme Nao Encontrado");
        detalheErro.setTimeStamp(System.currentTimeMillis());
        detalheErro.setMensagem(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalheErro);
    }
    
    @ExceptionHandler(NaoAfetadoException.class)
    public ResponseEntity<DetalheErro> handlerNaoAfetadoException(NaoAfetadoException e, HttpServletRequest request) {
        DetalheErro detalheErro = new DetalheErro();
        detalheErro.setStatus(200l);
        detalheErro.setTitulo("Avaliacao nao pode ser salva");
        detalheErro.setTimeStamp(System.currentTimeMillis());
        detalheErro.setMensagem(e.getMessage());
        return ResponseEntity.status(HttpStatus.OK).body(detalheErro);
    }

}
