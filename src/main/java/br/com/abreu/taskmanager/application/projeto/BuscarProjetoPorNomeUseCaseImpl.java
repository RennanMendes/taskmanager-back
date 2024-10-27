package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorNomeUseCase;
import br.com.abreu.taskmanager.core.entities.Projeto;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public class BuscarProjetoPorNomeUseCaseImpl implements BuscarProjetoPorNomeUseCase {

    private final ProjetoRepositoryService repository;

    public BuscarProjetoPorNomeUseCaseImpl(ProjetoRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "projetos", key = "#nome")
    public List<Projeto> buscar(String nome) {
        return repository.buscarPorNome(nome);
    }
}