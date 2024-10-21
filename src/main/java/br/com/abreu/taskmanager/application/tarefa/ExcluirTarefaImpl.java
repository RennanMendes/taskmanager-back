package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.tarefa.BuscarTarefaPorId;
import br.com.abreu.taskmanager.core.cases.tarefa.ExcluirTarefa;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.UUID;

public class ExcluirTarefaImpl implements ExcluirTarefa {

    private final TarefaRepositoryService repository;
    private final BuscarTarefaPorId buscarTarefaPorId;

    public ExcluirTarefaImpl(TarefaRepositoryService repository, BuscarTarefaPorId buscarTarefaPorId) {
        this.repository = repository;
        this.buscarTarefaPorId = buscarTarefaPorId;
    }

    @Override
    public void excluir(UUID id) {
        Tarefa tarefa = buscarTarefaPorId.buscarPorId(id);
         repository.deletar(tarefa);
    }
}