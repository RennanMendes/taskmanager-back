package br.com.abreu.taskmanager.api.dto;

import br.com.abreu.taskmanager.core.entities.Prioridade;
import br.com.abreu.taskmanager.core.entities.Status;

import java.time.LocalDate;

public record TarefaRequestDto(
        String titulo,
        String descricao,
        LocalDate prazo,
        LocalDate dataInicio,
        LocalDate dataFim,
        Status status,
        Prioridade prioridade,
        String responsavel
) {
}