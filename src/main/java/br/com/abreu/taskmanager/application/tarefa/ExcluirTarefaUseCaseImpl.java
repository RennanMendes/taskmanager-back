package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.tarefa.BuscarTarefaPorIdUseCase;
import br.com.abreu.taskmanager.core.cases.tarefa.ExcluirTarefaUseCase;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.UUID;

public class ExcluirTarefaUseCaseImpl implements ExcluirTarefaUseCase {

    private final TarefaRepositoryService repository;
    private final BuscarTarefaPorIdUseCase buscarTarefaPorIdUseCase;

    public ExcluirTarefaUseCaseImpl(TarefaRepositoryService repository, BuscarTarefaPorIdUseCase buscarTarefaPorIdUseCase) {
        this.repository = repository;
        this.buscarTarefaPorIdUseCase = buscarTarefaPorIdUseCase;
    }

    @Override
    public void excluir(UUID id) {
        Tarefa tarefa = buscarTarefaPorIdUseCase.buscarPorId(id);
        repository.deletar(tarefa);
    }
}