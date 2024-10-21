package br.com.abreu.taskmanager.core.cases.tarefa;

import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.List;

public interface FiltarTarefaPorStatus {
    List<Tarefa> filtrar(String status);
}
