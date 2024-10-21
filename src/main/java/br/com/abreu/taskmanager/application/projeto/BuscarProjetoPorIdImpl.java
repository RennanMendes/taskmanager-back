package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.application.exception.ProjetoNaoEncontradoException;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorId;
import br.com.abreu.taskmanager.core.entities.Projeto;

import java.util.UUID;


public class BuscarProjetoPorIdImpl implements BuscarProjetoPorId {

    private final ProjetoRepositoryService repository;

    public BuscarProjetoPorIdImpl(ProjetoRepositoryService repository) {
        this.repository = repository;
    }

    @Override
    public Projeto buscar(UUID id) {
        return repository.buscarPorId(id).orElseThrow(ProjetoNaoEncontradoException::new);
    }
}