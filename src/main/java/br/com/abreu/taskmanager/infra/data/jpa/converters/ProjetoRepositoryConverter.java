package br.com.abreu.taskmanager.infra.data.jpa.converters;

import br.com.abreu.taskmanager.core.entities.Projeto;
import br.com.abreu.taskmanager.infra.data.jpa.entity.ProjetoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class ProjetoRepositoryConverter implements RepositoryConverter<ProjetoEntity, Projeto> {

    @Autowired
    @Lazy
    private TarefaRepositoryConverter converter;

    @Override
    public ProjetoEntity mapToTable(Projeto persistenceObject) {
        return new ProjetoEntity(
                persistenceObject.getId(),
                persistenceObject.getNome(),
                persistenceObject.getDescricao(),
                persistenceObject.getDataInicio(),
                persistenceObject.getDataFim(),
                persistenceObject.getStatus(),
                persistenceObject.getPrioridade(),
                Optional.ofNullable(persistenceObject.getTarefas())
                        .map(tarefas -> tarefas.stream().map(converter::mapToTable).toList())
                        .orElse(Collections.emptyList()) // Retorna uma lista vazia se getTarefas() for null
        );
    }

    @Override
    public Projeto mapToEntity(ProjetoEntity entityObject) {
        return new Projeto(
                entityObject.getId(),
                entityObject.getNome(),
                entityObject.getDescricao(),
                entityObject.getDataInicio(),
                entityObject.getDataFim(),
                entityObject.getStatus(),
                entityObject.getPrioridade(),
                Optional.ofNullable(entityObject.getTarefas())
                        .map(tarefas -> tarefas.stream().map(converter::mapToEntity).toList())
                        .orElse(Collections.emptyList())
        );
    }
}