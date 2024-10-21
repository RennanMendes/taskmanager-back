package br.com.abreu.taskmanager.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Tarefa {
    private UUID id;
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Status status;
    private Prioridade prioridade;
    private String responsavel;
    private Projeto projeto;
}