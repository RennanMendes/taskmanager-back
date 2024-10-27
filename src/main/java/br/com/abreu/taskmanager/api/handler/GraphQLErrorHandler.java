package br.com.abreu.taskmanager.api.handler;

import br.com.abreu.taskmanager.application.exception.ProjetoNaoEncontradoException;
import br.com.abreu.taskmanager.application.exception.TarefaNaoEncontradaException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GraphQLErrorHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof ProjetoNaoEncontradoException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(ErrorType.NOT_FOUND)
                    .build();
        } else if (ex instanceof TarefaNaoEncontradaException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .errorType(ErrorType.NOT_FOUND)
                    .build();
        }

        return super.resolveToSingleError(ex, env);
    }
}