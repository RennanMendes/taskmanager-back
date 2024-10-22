package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.CriarProjetoUseCase;
import br.com.abreu.taskmanager.core.entities.Projeto;

public class CriarProjetoUseCaseImpl implements CriarProjetoUseCase {

    private final ProjetoRepositoryService repository;

    public CriarProjetoUseCaseImpl(ProjetoRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public Projeto criar(Projeto projeto) {
        return repository.salvar(projeto);
    }
}