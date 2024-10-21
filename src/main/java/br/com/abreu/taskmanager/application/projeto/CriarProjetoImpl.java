package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.CriarProjeto;
import br.com.abreu.taskmanager.core.entities.Projeto;

public class CriarProjetoImpl implements CriarProjeto {

    private ProjetoRepositoryService repository;

    public CriarProjetoImpl(ProjetoRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public Projeto criar(Projeto projeto) {
        return repository.salvar(projeto);
    }
}