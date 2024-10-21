package br.com.abreu.taskmanager.api.controller;

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

    @Autowired
    private BuscarProjetoPorId buscarProjetoPorId;

    @Autowired
    private BuscarProjetoPorNome buscarProjetoPorNome;

    @Autowired
    private BuscarTodosProjetos buscarTodosProjetos;

    @Autowired
    private CriarProjeto criarProjeto;

    @Autowired
    private ExcluirProjeto excluirProjeto;

    @QueryMapping
    public Projeto buscarPorId(@Argument UUID id) {
        return buscarProjetoPorId.buscar(id);
    }

    @QueryMapping
    public List<Projeto> buscarPorNome(@Argument String nome) {
        return buscarProjetoPorNome.buscar(nome);
    }

    @QueryMapping
    public List<Projeto> buscarTodos() {
        return buscarTodosProjetos.buscar();
    }

    @MutationMapping
    public Projeto criar( @Argument Projeto projeto){
        return criarProjeto.criar(projeto);
    }

    // TODO-> Criar objeto de retorno para exclusão
    @MutationMapping
    public Boolean excluir(@Argument UUID id){
        excluirProjeto.excluir(id);
        return true;
    }

}