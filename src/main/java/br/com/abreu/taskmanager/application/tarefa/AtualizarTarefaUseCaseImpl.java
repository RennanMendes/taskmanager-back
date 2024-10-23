package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.tarefa.AtualizarTarefaUseCase;
import br.com.abreu.taskmanager.core.cases.tarefa.BuscarTarefaPorIdUseCase;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.UUID;

public class AtualizarTarefaUseCaseImpl implements AtualizarTarefaUseCase {

    private final TarefaRepositoryService repositoryService;
    private final BuscarTarefaPorIdUseCase buscarTarefaPorIdUseCase;

    public AtualizarTarefaUseCaseImpl(TarefaRepositoryService repositoryService, BuscarTarefaPorIdUseCase buscarTarefaPorIdUseCase) {
        this.repositoryService = repositoryService;
        this.buscarTarefaPorIdUseCase = buscarTarefaPorIdUseCase;
    }

    @Override
    public Tarefa atualizar(UUID id, Tarefa dto) {
        Tarefa tarefa = buscarTarefaPorIdUseCase.buscarPorId(id);
        return  repositoryService.save(tarefa.atualizar(dto));
    }
}