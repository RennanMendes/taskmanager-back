package br.com.abreu.taskmanager.api.handler;

import br.com.abreu.taskmanager.application.exception.ProjetoNaoEncontradoException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.ErrorType;

public class ProjetoNaoEncontradoExceptionMapper implements GraphQLExceptionMapper<ProjetoNaoEncontradoException> {

    @Override
    public GraphQLError toGraphQLError(Throwable exception, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError(env)
                .message(exception.getMessage())
                .errorType(ErrorType.NOT_FOUND)
                .build();
    }

    @Override
    public Class<ProjetoNaoEncontradoException> getExceptionType() {
        return ProjetoNaoEncontradoException.class;
    }
}