package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorNome;
import br.com.abreu.taskmanager.core.entities.Projeto;

import java.util.List;

public class BuscarProjetoPorNomeImpl implements BuscarProjetoPorNome {

    private final ProjetoRepositoryService repository;

    public BuscarProjetoPorNomeImpl(ProjetoRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public List<Projeto> buscar(String nome) {
        return repository.buscarPorNome(nome);
    }
}