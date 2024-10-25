package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.application.exception.TarefaNaoEncontradaException;
import br.com.abreu.taskmanager.core.cases.tarefa.BuscarTarefasPorProjetoUseCase;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.List;
import java.util.UUID;

public class BuscarTarefasPorProjetoUseCaseImpl implements BuscarTarefasPorProjetoUseCase {

    private final TarefaRepositoryService repository;

    public BuscarTarefasPorProjetoUseCaseImpl(TarefaRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public List<Tarefa> buscarPorProjeto(UUID idProjeto) {
        if (!repository.existeTarefaPorProjetoId(idProjeto)) {
            throw new TarefaNaoEncontradaException();
        }

        return repository.buscarPorProjeto(idProjeto);
    }
}