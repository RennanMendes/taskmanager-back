package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarTodosProjetos;
import br.com.abreu.taskmanager.core.entities.Projeto;

import java.util.List;

public class BuscarTodosProjetosImpl implements BuscarTodosProjetos {

    private ProjetoRepositoryService repository;

    public BuscarTodosProjetosImpl(ProjetoRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public List<Projeto> buscar() {
        return repository.buscarTodos();
    }
}
