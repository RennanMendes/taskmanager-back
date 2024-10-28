package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarTodosProjetosUseCase;
import br.com.abreu.taskmanager.core.entities.Projeto;

import java.util.List;

public class BuscarTodosProjetosUseCaseImpl implements BuscarTodosProjetosUseCase {

    private final ProjetoRepositoryService repository;

    public BuscarTodosProjetosUseCaseImpl(ProjetoRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public List<Projeto> buscar() {
        return repository.buscarTodos();
    }
}