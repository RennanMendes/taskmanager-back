package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.tarefa.BuscarTarefasPorProjeto;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.List;
import java.util.UUID;

public class BuscarTarefasPorProjetoImpl implements BuscarTarefasPorProjeto {

    private final TarefaRepositoryService repository;

    public BuscarTarefasPorProjetoImpl(TarefaRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public List<Tarefa> buscarPorProjeto(UUID idProjeto) {
        return repository.buscarPorProjeto(idProjeto);
    }
}