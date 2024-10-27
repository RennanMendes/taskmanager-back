package br.com.abreu.taskmanager.api.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class GraphQLExceptionMapperRegistry {

    private final Map<Class<? extends Throwable>, GraphQLExceptionMapper<?>> mappers = new HashMap<>();

    public GraphQLExceptionMapperRegistry() {
        // Registra mappers
        addMapper(new ProjetoNaoEncontradoExceptionMapper());
        addMapper(new TarefaNaoEncontradaExceptionMapper());
    }

    private <T extends Throwable> void addMapper(GraphQLExceptionMapper<T> mapper) {
        mappers.put(mapper.getExceptionType(), mapper);
    }

    public Optional<GraphQLExceptionMapper<?>> getMapper(Class<? extends Throwable> exceptionClass) {
        return Optional.ofNullable(mappers.get(exceptionClass));
    }
}