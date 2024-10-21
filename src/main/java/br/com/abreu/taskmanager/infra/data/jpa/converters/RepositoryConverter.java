package br.com.abreu.taskmanager.infra.data.jpa.converters;

public interface RepositoryConverter<T, E> {

    default T mapToTable(final E persistenceObject) {
        throw new UnsupportedOperationException();
    }

    default E mapToEntity(final T tableObject) {
        throw new UnsupportedOperationException();
    }
}