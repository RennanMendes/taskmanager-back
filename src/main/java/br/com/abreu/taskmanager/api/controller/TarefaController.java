package br.com.abreu.taskmanager.api.controller;

import br.com.abreu.taskmanager.api.converter.TarefaConverter;
import br.com.abreu.taskmanager.api.dto.TarefaRequestDto;
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
    private final AtualizarTarefaUseCase atualizarTarefaUseCase;
    private final TarefaConverter converter;

    @Autowired
    public TarefaController(BuscarTarefaPorIdUseCase buscarTarefaPorIdUseCase, BuscarTarefasPorProjetoUseCase buscarTarefasPorProjetoUseCase,
                            CriarTarefaUseCase criarTarefaUseCase, ExcluirTarefaUseCase excluirTarefaUseCase,
                            FiltarTarefaPorStatusUseCase filtarTarefaPorStatusUseCase, AtualizarTarefaUseCase atualizarTarefaUseCase, TarefaConverter converter) {
        this.buscarTarefaPorIdUseCase = buscarTarefaPorIdUseCase;
        this.buscarTarefasPorProjetoUseCase = buscarTarefasPorProjetoUseCase;
        this.criarTarefaUseCase = criarTarefaUseCase;
        this.excluirTarefaUseCase = excluirTarefaUseCase;
        this.filtarTarefaPorStatusUseCase = filtarTarefaPorStatusUseCase;
        this.atualizarTarefaUseCase = atualizarTarefaUseCase;
        this.converter = converter;
    }

    @QueryMapping
    public Tarefa buscarTarefaPorId(@Argument UUID id) {
        return buscarTarefaPorIdUseCase.buscarPorId(id);
    }

    @QueryMapping
    public List<Tarefa> buscarTarefasPorProjetoId(@Argument UUID idProjeto) {
        return buscarTarefasPorProjetoUseCase.buscarPorProjeto(idProjeto);
    }

    @QueryMapping
    public List<Tarefa> filtarTarefaPorProjetoIdEStatus(@Argument UUID idProjeto, @Argument Status status) {
        return filtarTarefaPorStatusUseCase.filtrarPorIdEStatus(idProjeto, status);
    }

    @MutationMapping
    public Tarefa criarTarefa(@Argument UUID idProjeto, @Argument TarefaRequestDto tarefa) {
        return criarTarefaUseCase.criar(idProjeto, converter.dtoToEntity(tarefa));
    }

    @MutationMapping
    public Tarefa atualizarTarefa(@Argument UUID id, @Argument TarefaRequestDto tarefa) {
        return atualizarTarefaUseCase.atualizar(id, converter.dtoToEntity(tarefa));
    }

    @MutationMapping
    public Boolean excluirTarefa(@Argument UUID id) {
        excluirTarefaUseCase.excluir(id);
        return true;
    }

}