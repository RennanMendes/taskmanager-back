package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.tarefa.FiltarTarefaPorStatusUseCase;
import br.com.abreu.taskmanager.core.entities.Status;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.List;
import java.util.UUID;

public class FiltarTarefaPorStatusUseCaseImpl implements FiltarTarefaPorStatusUseCase {

    private final TarefaRepositoryService repository;

    public FiltarTarefaPorStatusUseCaseImpl(TarefaRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public List<Tarefa> filtrarPorIdEStatus(UUID idProjeto, Status status) {
        return repository.filtarPorIdEStatus(idProjeto, status);
    }
}