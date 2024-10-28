package br.com.abreu.taskmanager.core.cases.tarefa;

import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.UUID;

public interface CriarTarefaUseCase {
    Tarefa criar(UUID idProjeto, Tarefa tarefa);
}