package br.com.abreu.taskmanager.infra.config;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.application.projeto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjetoConfig {

    @Autowired
    ProjetoRepositoryService repository;

    @Bean
    public BuscarProjetoPorIdImpl buscarProjetoPorId(ProjetoRepositoryService repository) {
        return new BuscarProjetoPorIdImpl(repository);
    }

    @Bean
    public BuscarProjetoPorNomeImpl buscarProjetoPorNome(ProjetoRepositoryService repository) {
        return new BuscarProjetoPorNomeImpl(repository);
    }

    @Bean
    public BuscarTodosProjetosImpl buscarTodosProjetos(ProjetoRepositoryService repository) {
        return new BuscarTodosProjetosImpl(repository);
    }

    @Bean
    public CriarProjetoImpl criarProjeto(ProjetoRepositoryService repository) {
        return new CriarProjetoImpl(repository);
    }

    @Bean
    public ExcluirProjetoImpl excluirProjeto(ProjetoRepositoryService repository, BuscarProjetoPorIdImpl buscarProjetoPorId) {
        return new ExcluirProjetoImpl(repository, buscarProjetoPorId);
    }
}