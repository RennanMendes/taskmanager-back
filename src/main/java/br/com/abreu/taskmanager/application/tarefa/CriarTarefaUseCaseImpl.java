package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorId;
import br.com.abreu.taskmanager.core.cases.tarefa.CriarTarefaUseCase;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.UUID;

public class CriarTarefaUseCaseImpl implements CriarTarefaUseCase {

    private final TarefaRepositoryService repository;
    private final BuscarProjetoPorId buscarProjetoPorId;

    public CriarTarefaUseCaseImpl(TarefaRepositoryService repository, BuscarProjetoPorId buscarProjetoPorId) {
        this.repository = repository;
        this.buscarProjetoPorId = buscarProjetoPorId;
    }

    @Override
    public Tarefa criar(UUID idProjeto, Tarefa tarefa) {
        tarefa.setProjeto(buscarProjetoPorId.buscar(idProjeto));
        return repository.save(tarefa);
    }
}