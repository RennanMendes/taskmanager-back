package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorIdUseCase;
import br.com.abreu.taskmanager.core.cases.projeto.ExcluirProjetoUseCase;
import br.com.abreu.taskmanager.core.entities.Projeto;

import java.util.UUID;


public class ExcluirProjetoUseCaseImpl implements ExcluirProjetoUseCase {

    private final ProjetoRepositoryService repository;
    private final BuscarProjetoPorIdUseCase buscarProjetoPorId;

    public ExcluirProjetoUseCaseImpl(ProjetoRepositoryService repository, BuscarProjetoPorIdUseCase buscarProjetoPorId) {
        this.repository = repository;
        this.buscarProjetoPorId = buscarProjetoPorId;
    }

    @Override
    public void excluir(UUID id) {
        Projeto projeto = buscarProjetoPorId.buscar(id);
        repository.deletar(projeto);
    }
}