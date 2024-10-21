package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.tarefa.CriarTarefa;
import br.com.abreu.taskmanager.core.entities.Tarefa;

public class CriarTarefaImpl implements CriarTarefa {

    private TarefaRepositoryService repository;

    public CriarTarefaImpl(TarefaRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public Tarefa criar(Tarefa tarefa) {
        return repository.save(tarefa);
    }
}
