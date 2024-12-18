package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.application.exception.ProjetoNaoEncontradoException;
import br.com.abreu.taskmanager.common.aop.CacheAnnotation;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorIdUseCase;
import br.com.abreu.taskmanager.core.entities.Projeto;
import org.springframework.cache.annotation.Cacheable;

import java.util.UUID;


public class BuscarProjetoPorIdUseCaseImpl implements BuscarProjetoPorIdUseCase {

    private final ProjetoRepositoryService repository;

    public BuscarProjetoPorIdUseCaseImpl(ProjetoRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "projetosId", key = "#id")
    public Projeto buscar(UUID id) {
        return repository.buscarPorId(id).orElseThrow(ProjetoNaoEncontradoException::new);
    }
}