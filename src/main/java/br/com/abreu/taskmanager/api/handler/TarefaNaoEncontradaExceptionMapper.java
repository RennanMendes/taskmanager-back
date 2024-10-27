package br.com.abreu.taskmanager.api.handler;

import br.com.abreu.taskmanager.application.exception.ProjetoNaoEncontradoException;
import br.com.abreu.taskmanager.application.exception.TarefaNaoEncontradaException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.ErrorType;

public class TarefaNaoEncontradaExceptionMapper implements GraphQLExceptionMapper<TarefaNaoEncontradaException> {

    @Override
    public GraphQLError toGraphQLError(Throwable exception, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(exception.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .build();
    }

    @Override
    public Class<TarefaNaoEncontradaException> getExceptionType() {
        return TarefaNaoEncontradaException.class;
    }
}