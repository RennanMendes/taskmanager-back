package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorIdUseCase;
import br.com.abreu.taskmanager.core.cases.tarefa.BuscarTarefasPorProjetoUseCase;
import br.com.abreu.taskmanager.core.entities.Tarefa;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.UUID;

public class BuscarTarefasPorProjetoUseCaseImpl implements BuscarTarefasPorProjetoUseCase {

    private final TarefaRepositoryService repository;
    private final BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase;

    public BuscarTarefasPorProjetoUseCaseImpl(TarefaRepositoryService repository, BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase) {
        this.repository = repository;
        this.buscarProjetoPorIdUseCase = buscarProjetoPorIdUseCase;
    }

    @Override
    @Cacheable(value = "tarefas", key = "#idProjeto")
    public List<Tarefa> buscarPorProjeto(UUID idProjeto) {
        buscarProjetoPorIdUseCase.buscar(idProjeto);
        return repository.buscarPorProjeto(idProjeto);
    }
}