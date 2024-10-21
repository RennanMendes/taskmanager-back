package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.tarefa.ExcluirTarefa;

import java.util.UUID;

public class ExcluirTarefaImpl implements ExcluirTarefa {

    private TarefaRepositoryService repository;

    public ExcluirTarefaImpl(TarefaRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public void excluir(UUID id) {
         repository.deletar(id);
    }
}
