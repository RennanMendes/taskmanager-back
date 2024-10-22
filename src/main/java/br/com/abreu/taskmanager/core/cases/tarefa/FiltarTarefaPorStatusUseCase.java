package br.com.abreu.taskmanager.core.cases.tarefa;

import br.com.abreu.taskmanager.core.entities.Status;
import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.List;
import java.util.UUID;

public interface FiltarTarefaPorStatusUseCase {
    List<Tarefa> filtrarPorIdEStatus(UUID id, Status status);
}
