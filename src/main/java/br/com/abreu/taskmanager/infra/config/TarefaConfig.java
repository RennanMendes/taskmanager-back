package br.com.abreu.taskmanager.infra.config;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.application.tarefa.*;
import br.com.abreu.taskmanager.core.cases.tarefa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TarefaConfig {

    @Autowired
    BuscarTarefaPorId repository;

    @Bean
    public BuscarTarefaPorId buscarTarefaPorId(TarefaRepositoryService repository) {
        return new BuscarTarefaPorIdImpl(repository);
    }

    @Bean
    public BuscarTarefasPorProjeto buscarTarefasPorProjeto(TarefaRepositoryService repository) {
        return new BuscarTarefasPorProjetoImpl(repository);
    }

    @Bean
    public CriarTarefa criarTarefa(TarefaRepositoryService repository) {
        return new CriarTarefaImpl(repository);
    }

    @Bean
    public ExcluirTarefa excluirTarefa(TarefaRepositoryService repository, BuscarTarefaPorId buscarTarefaPorId) {
        return new ExcluirTarefaImpl(repository, buscarTarefaPorId);
    }

    @Bean
    public FiltarTarefaPorStatus filtarTarefaPorStatus(TarefaRepositoryService repository) {
        return new FiltarTarefaPorStatusImpl(repository);
    }
}