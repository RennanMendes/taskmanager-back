package br.com.abreu.taskmanager.api.controller;

import br.com.abreu.taskmanager.core.cases.tarefa.*;
import br.com.abreu.taskmanager.core.entities.Status;
import br.com.abreu.taskmanager.core.entities.Tarefa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class TarefaController {
    
    private final BuscarTarefaPorIdUseCase buscarTarefaPorIdUseCase;
    private final BuscarTarefasPorProjetoUseCase buscarTarefasPorProjetoUseCase;
    private final CriarTarefaUseCase criarTarefaUseCase;
    private final ExcluirTarefaUseCase excluirTarefaUseCase;
    private final FiltarTarefaPorStatusUseCase filtarTarefaPorStatusUseCase;

    @Autowired
    public TarefaController(BuscarTarefaPorIdUseCase buscarTarefaPorIdUseCase, BuscarTarefasPorProjetoUseCase buscarTarefasPorProjetoUseCase,
                            CriarTarefaUseCase criarTarefaUseCase, ExcluirTarefaUseCase excluirTarefaUseCase, FiltarTarefaPorStatusUseCase filtarTarefaPorStatusUseCase) {
        this.buscarTarefaPorIdUseCase = buscarTarefaPorIdUseCase;
        this.buscarTarefasPorProjetoUseCase = buscarTarefasPorProjetoUseCase;
        this.criarTarefaUseCase = criarTarefaUseCase;
        this.excluirTarefaUseCase = excluirTarefaUseCase;
        this.filtarTarefaPorStatusUseCase = filtarTarefaPorStatusUseCase;
    }

    @QueryMapping
    public Tarefa buscarTarefaPorId(@Argument UUID id){
        return buscarTarefaPorIdUseCase.buscarPorId(id);
    }

    @QueryMapping
    public List<Tarefa> buscarTarefasPorProjeto(@Argument UUID idProjeto){
        return buscarTarefasPorProjetoUseCase.buscarPorProjeto(idProjeto);
    }

    @QueryMapping
    public List<Tarefa> filtarTarefaPorStatus(UUID idProjeto, Status status){
        return filtarTarefaPorStatusUseCase.filtrarPorIdEStatus(idProjeto, status);
    }

    @MutationMapping
    public Tarefa criarTarefa(@Argument UUID idProjeto, @Argument Tarefa tarefa){
        return criarTarefaUseCase.criar(idProjeto, tarefa);
    }

}