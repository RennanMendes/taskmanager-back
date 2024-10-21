package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.tarefa.FiltarTarefaPorStatus;
import br.com.abreu.taskmanager.core.entities.Status;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.List;
import java.util.UUID;

public class FiltarTarefaPorStatusImpl implements FiltarTarefaPorStatus {

    private final TarefaRepositoryService repository;

    public FiltarTarefaPorStatusImpl(TarefaRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public List<Tarefa> filtrarPorIdEStatus(UUID id, Status status) {
        return repository.filtarPorIdEStatus(id, status);
    }
}