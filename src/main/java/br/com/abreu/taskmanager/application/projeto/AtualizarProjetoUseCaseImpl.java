package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.AtualizarProjetoUseCase;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorIdUseCase;
import br.com.abreu.taskmanager.core.entities.Projeto;

import java.util.UUID;

public class AtualizarProjetoUseCaseImpl implements AtualizarProjetoUseCase {

    private final ProjetoRepositoryService repository;
    private final BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase;

    public AtualizarProjetoUseCaseImpl(ProjetoRepositoryService repository, BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase) {
        this.repository = repository;
        this.buscarProjetoPorIdUseCase = buscarProjetoPorIdUseCase;
    }

    @Override
    public Projeto atualizar(UUID id, Projeto dto) {
        Projeto projeto = buscarProjetoPorIdUseCase.buscar(id);
        return repository.salvar(projeto.atualizar(dto));
    }
}