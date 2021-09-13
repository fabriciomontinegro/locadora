package com.br.locadora.exception;

public class FilmeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FilmeNotFoundException(String mensagem) {
        super(mensagem);
    }

    public FilmeNotFoundException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

}
