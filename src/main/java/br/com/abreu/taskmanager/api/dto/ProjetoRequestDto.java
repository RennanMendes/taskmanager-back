package br.com.abreu.taskmanager.api.dto;

import br.com.abreu.taskmanager.core.entities.Prioridade;
import br.com.abreu.taskmanager.core.entities.Status;

import java.time.LocalDate;

public record ProjetoRequestDto(
        String nome,
        String descricao,
        LocalDate dataInicio,
        LocalDate dataFim,
        Status status,
        Prioridade prioridade
) {
}
