package br.com.abreu.taskmanager.api.handler;

import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;

public interface GraphQLExceptionMapper<T extends Throwable> {
    GraphQLError toGraphQLError(Throwable exception, DataFetchingEnvironment env);

    Class<T> getExceptionType();
}