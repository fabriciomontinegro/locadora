package com.br.locadora.exception;

public class NaoAfetadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NaoAfetadoException(String mensagem) {
        super(mensagem);
    }

    public NaoAfetadoException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
    
}
