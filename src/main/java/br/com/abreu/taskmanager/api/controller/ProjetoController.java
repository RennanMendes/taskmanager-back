package br.com.abreu.taskmanager.api.controller;

import br.com.abreu.taskmanager.api.converter.ProjetoConverter;
import br.com.abreu.taskmanager.api.dto.ProjetoRequestDto;
import br.com.abreu.taskmanager.core.cases.projeto.*;
import br.com.abreu.taskmanager.core.entities.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class ProjetoController {

    private final BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase;
    private final BuscarProjetoPorNomeUseCase buscarProjetoPorNomeUseCase;
    private final BuscarTodosProjetosUseCase buscarTodosProjetosUseCase;
    private final CriarProjetoUseCase criarProjetoUseCase;
    private final ExcluirProjetoUseCase excluirProjetoUseCase;
    private final AtualizarProjetoUseCase atualizarProjetoUseCase;
    private final ProjetoConverter converter;

    @Autowired
    public ProjetoController(BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase, BuscarProjetoPorNomeUseCase
            buscarProjetoPorNomeUseCase, BuscarTodosProjetosUseCase buscarTodosProjetosUseCase,
                             CriarProjetoUseCase criarProjetoUseCase, ExcluirProjetoUseCase excluirProjetoUseCase,
                             AtualizarProjetoUseCase atualizarProjetoUseCase, ProjetoConverter converter) {

        this.buscarProjetoPorIdUseCase = buscarProjetoPorIdUseCase;
        this.buscarProjetoPorNomeUseCase = buscarProjetoPorNomeUseCase;
        this.buscarTodosProjetosUseCase = buscarTodosProjetosUseCase;
        this.criarProjetoUseCase = criarProjetoUseCase;
        this.excluirProjetoUseCase = excluirProjetoUseCase;
        this.atualizarProjetoUseCase = atualizarProjetoUseCase;
        this.converter = converter;
    }

    @QueryMapping
    public Projeto buscarProjetoPorId(@Argument UUID id) {
        return buscarProjetoPorIdUseCase.buscar(id);
    }

    @QueryMapping
    public List<Projeto> buscarProjetoPorNome(@Argument String nome) {
        return buscarProjetoPorNomeUseCase.buscar(nome);
    }

    @QueryMapping
    public List<Projeto> buscarTodosOsProjetos() {
        return buscarTodosProjetosUseCase.buscar();
    }

    @MutationMapping
    public Projeto criarProjeto(@Argument ProjetoRequestDto projeto) {
        return criarProjetoUseCase.criar(converter.dtoToEntity(projeto));
    }

    @MutationMapping
    public Projeto atualizarProjeto(@Argument UUID id, @Argument ProjetoRequestDto projeto) {
        return atualizarProjetoUseCase.atualizar(id, converter.dtoToEntity(projeto));
    }

    @MutationMapping
    public Boolean excluirProjeto(@Argument UUID id) {
        excluirProjetoUseCase.excluir(id);
        return true;
    }

}