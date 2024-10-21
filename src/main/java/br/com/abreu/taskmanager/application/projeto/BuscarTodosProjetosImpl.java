package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarTodosProjetos;
import br.com.abreu.taskmanager.core.entities.Projeto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class BuscarTodosProjetosImpl implements BuscarTodosProjetos {

    private ProjetoRepositoryService repository;

    @Override
    public List<Projeto> buscar() {
        return repository.buscarTodos();
    }
}
