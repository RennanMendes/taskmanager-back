package br.com.abreu.taskmanager.infra.config;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.application.projeto.*;
import br.com.abreu.taskmanager.core.cases.projeto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjetoConfig {

    @Autowired
    ProjetoRepositoryService repository;

    @Bean
    public AtualizarProjetoUseCase atualizarProjetoUseCase(ProjetoRepositoryService repository, BuscarProjetoPorIdUseCase buscarProjetoPorId){
        return new AtualizarProjetoUseCaseImpl(repository, buscarProjetoPorId);
    }

    @Bean
    public BuscarProjetoPorIdUseCase buscarProjetoPorId(ProjetoRepositoryService repository) {
        return new BuscarProjetoPorIdUseCaseImpl(repository);
    }

    @Bean
    public BuscarProjetoPorNomeUseCase buscarProjetoPorNome(ProjetoRepositoryService repository) {
        return new BuscarProjetoPorNomeUseCaseImpl(repository);
    }

    @Bean
    public BuscarTodosProjetosUseCase buscarTodosProjetos(ProjetoRepositoryService repository) {
        return new BuscarTodosProjetosUseCaseImpl(repository);
    }

    @Bean
    public CriarProjetoUseCase criarProjeto(ProjetoRepositoryService repository) {
        return new CriarProjetoUseCaseImpl(repository);
    }

    @Bean
    public ExcluirProjetoUseCase excluirProjeto(ProjetoRepositoryService repository, BuscarProjetoPorIdUseCase buscarProjetoPorId) {
        return new ExcluirProjetoUseCaseImpl(repository, buscarProjetoPorId);
    }
}