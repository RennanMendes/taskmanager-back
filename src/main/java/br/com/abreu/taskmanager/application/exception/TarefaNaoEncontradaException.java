package br.com.abreu.taskmanager.application.exception;

public class TarefaNaoEncontradaException extends RuntimeException {

    public TarefaNaoEncontradaException() {
        super("Tarefa não encontrada!");
    }
}
