package br.com.abreu.taskmanager.api.converter;

import br.com.abreu.taskmanager.api.dto.ProjetoRequestDto;
import br.com.abreu.taskmanager.core.entities.Projeto;
import org.springframework.stereotype.Component;

@Component
public class ProjetoConverter implements DtoConverter<Projeto, ProjetoRequestDto> {

    @Override
    public Projeto dtoToEntity(ProjetoRequestDto dto) {
        return new Projeto(null, dto.nome(), dto.descricao(), dto.dataInicio(),
                dto.dataFim(), dto.status(), dto.prioridade());
    }

}
