package br.com.abreu.taskmanager.api.converter;

import br.com.abreu.taskmanager.api.dto.TarefaRequestDto;
import br.com.abreu.taskmanager.core.entities.Tarefa;
import org.springframework.stereotype.Component;

@Component
public class TarefaConverter implements DtoConverter<Tarefa, TarefaRequestDto> {

    @Override
    public Tarefa dtoToEntity(TarefaRequestDto dto) {
        return new Tarefa(null, dto.titulo(), dto.descricao(), dto.prazo(), dto.dataInicio(),
                dto.dataFim(), dto.status(), dto.prioridade(), dto.responsavel(), null);
    }

}