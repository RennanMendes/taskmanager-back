package br.com.abreu.taskmanager.infra.data.jpa.converters;

import br.com.abreu.taskmanager.core.entities.Projeto;
import br.com.abreu.taskmanager.infra.data.jpa.entity.ProjetoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjetoRepositoryConverter implements RepositoryConverter<ProjetoEntity, Projeto> {

    @Override
    public ProjetoEntity mapToTable(Projeto persistenceObject) {
        return new ProjetoEntity(
                persistenceObject.getId(),
                persistenceObject.getNome(),
                persistenceObject.getDescricao(),
                persistenceObject.getDataInicio(),
                persistenceObject.getDataFim(),
                persistenceObject.getStatus(),
                persistenceObject.getPrioridade()
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
                entityObject.getPrioridade()
        );
    }
}