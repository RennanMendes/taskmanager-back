package br.com.abreu.taskmanager.api.converter;

public interface DtoConverter<A, B> {

    default A dtoToEntity(B dto) {
        throw new UnsupportedOperationException();
    }

}