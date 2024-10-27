package br.com.abreu.taskmanager.api.handler;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExceptionResolver extends DataFetcherExceptionResolverAdapter {

    private final GraphQLExceptionMapperRegistry mapperRegistry;

    public GraphQLExceptionResolver(GraphQLExceptionMapperRegistry mapperRegistry) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return mapperRegistry.getMapper(ex.getClass())
                .map(mapper -> mapper.toGraphQLError(ex, env))
                .orElseGet(() -> super.resolveToSingleError(ex, env));
    }
}