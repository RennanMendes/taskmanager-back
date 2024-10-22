package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorIdUseCase;
import br.com.abreu.taskmanager.core.cases.tarefa.CriarTarefaUseCase;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.UUID;

public class CriarTarefaUseCaseImpl implements CriarTarefaUseCase {

    private final TarefaRepositoryService repository;
    private final BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase;

    public CriarTarefaUseCaseImpl(TarefaRepositoryService repository, BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase) {
        this.repository = repository;
        this.buscarProjetoPorIdUseCase = buscarProjetoPorIdUseCase;
    }

    @Override
    public Tarefa criar(UUID idProjeto, Tarefa tarefa) {
        tarefa.setProjeto(buscarProjetoPorIdUseCase.buscar(idProjeto));
        return repository.save(tarefa);
    }
}