package br.com.abreu.taskmanager.infra.data.jpa.converters;

import br.com.abreu.taskmanager.core.entities.Tarefa;
import br.com.abreu.taskmanager.infra.data.jpa.entity.TarefaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TarefaRepositoryConverter implements RepositoryConverter<TarefaEntity, Tarefa> {

    @Autowired
    private ProjetoRepositoryConverter converter;

    @Override
    public TarefaEntity mapToTable(Tarefa persistenceObject) {
        return new TarefaEntity(
                persistenceObject.getId(),
                persistenceObject.getTitulo(),
                persistenceObject.getDescricao(),
                persistenceObject.getPrazo(),
                persistenceObject.getDataInicio(),
                persistenceObject.getDataFim(),
                persistenceObject.getStatus(),
                persistenceObject.getPrioridade(),
                persistenceObject.getResponsavel(),
                converter.mapToTable(persistenceObject.getProjeto())
        );
    }

    @Override
    public Tarefa mapToEntity(TarefaEntity entityObject) {
        return new Tarefa(
                entityObject.getId(),
                entityObject.getTitulo(),
                entityObject.getDescricao(),
                entityObject.getPrazo(),
                entityObject.getDataInicio(),
                entityObject.getDataFim(),
                entityObject.getStatus(),
                entityObject.getPrioridade(),
                entityObject.getResponsavel(),
                converter.mapToEntity(entityObject.getProjeto())
        );
    }
}