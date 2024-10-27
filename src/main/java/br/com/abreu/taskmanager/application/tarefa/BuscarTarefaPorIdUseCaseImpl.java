package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.application.exception.TarefaNaoEncontradaException;
import br.com.abreu.taskmanager.core.cases.tarefa.BuscarTarefaPorIdUseCase;
import br.com.abreu.taskmanager.core.entities.Tarefa;
import org.springframework.cache.annotation.Cacheable;

import java.util.UUID;

public class BuscarTarefaPorIdUseCaseImpl implements BuscarTarefaPorIdUseCase {

    private final TarefaRepositoryService repository;

    public BuscarTarefaPorIdUseCaseImpl(TarefaRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "tarefa", key = "#id")
    public Tarefa buscarPorId(UUID id) {
        return repository.buscarPorId(id).orElseThrow(TarefaNaoEncontradaException::new);
    }
}