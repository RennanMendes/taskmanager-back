package br.com.abreu.taskmanager.application.exception;

public class ProjetoNaoEncontradoException extends RuntimeException {
    public ProjetoNaoEncontradoException() {
        super("Projeto não encontrado!");
    }
}
