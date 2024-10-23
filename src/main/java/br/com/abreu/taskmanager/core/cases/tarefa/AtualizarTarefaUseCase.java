package br.com.abreu.taskmanager.core.cases.tarefa;

import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.UUID;

public interface AtualizarTarefaUseCase {
    Tarefa atualizar(UUID id, Tarefa tarefa);
}