package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.application.exception.TarefaNaoEncontradaException;
import br.com.abreu.taskmanager.core.cases.tarefa.BuscarTarefaPorIdUseCase;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.UUID;

public class BuscarTarefaPorIdImpl implements BuscarTarefaPorIdUseCase {

    private final TarefaRepositoryService repository;

    public BuscarTarefaPorIdImpl(TarefaRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public Tarefa buscarPorId(UUID id) {
        return repository.buscarPorId(id).orElseThrow(TarefaNaoEncontradaException::new);
    }
}