package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.ExcluirProjeto;
import br.com.abreu.taskmanager.core.entities.Projeto;

import java.util.UUID;


public class ExcluirProjetoImpl implements ExcluirProjeto {

    private ProjetoRepositoryService repository;
    private BuscarProjetoPorIdImpl buscarProjetoPorId;

    public ExcluirProjetoImpl(ProjetoRepositoryService repository, BuscarProjetoPorIdImpl buscarProjetoPorId) {
        this.repository = repository;
        this.buscarProjetoPorId = buscarProjetoPorId;
    }

    @Override
    public void excluir(UUID id) {
        Projeto projeto = buscarProjetoPorId.buscar(id);
        repository.deletar(projeto);
    }
}